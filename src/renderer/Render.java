package renderer;


import elements.Camera;
import elements.LightSource;
import geometries.*;
import primitives.*;

import scene.Scene;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import static primitives.Util.alignZero;
/**
 * class Render
 */
public class Render {
    private final ImageWriter _imageWriter;
    private final Scene _scene;
    /**
     * constant double for moving rays for shadows , reflections,etc
     */
    private static final double DELTA = 0.1;
    /**
     * constants for the base case of recursion
     */
    private static final int MAX_CALC_COLOR_LEVEL = 5;
    private static final double MIN_CALC_COLOR_K = 0.001;
    /**
     * parameters for ray tracing- glossy surface and diffuse glass - they are in class render because this class takes care of ray tracing
     */
    private int _numOfRays;

    private double _rayDistance;


    /**
     * bvh
     */
    private boolean _bvhtree;
    /**
     * parameters for threading
     */
    private int _threads = 1;
    private final int _spareThreads = 2;
    private boolean print = false;

    /**
     * set threads function
     * @param threads number of threads
     * @return Render
     */
    public Render set_threads(int threads) {
        if (threads < 0)
            throw new IllegalArgumentException("threading parameter cant be negative");

        if (threads == 0) {
            int cores = Runtime.getRuntime().availableProcessors() - _spareThreads;
            if (cores <= 2)
                _threads = 1;
            else
                _threads = cores;
        } else
            _threads = threads;
        return this;
    }

    /**
     * set print
     * @return Render
     */
    public Render setPrint() {
        this.print = true;
        return this;
    }

    /**
     * gets the distance we want between the ray point and the circle
     *
     * @return double - distance
     */
    public double get_rayDistance() {
        return _rayDistance;
    }

    /**
     * sets the distance between the ray point and the circle
     *
     * @param _rayDistance
     */
    public void set_rayDistance(double _rayDistance) {
        if (_rayDistance < 0)
            throw new IllegalArgumentException("distance cant be negative");
        this._rayDistance = _rayDistance;
    }

    /**
     * get number of rays function
     *
     * @return number of rays that will be part of the beam
     */
    public int get_numOfRays() {
        return _numOfRays;
    }

    /**
     * sets the number of rays that will be part of the beam
     *
     * @param _numOfRays int - amount of rays that will be part of the beam
     */
    public void set_numOfRays(int _numOfRays) {
        if (_numOfRays < 1)
            throw new IllegalArgumentException("there has to be at least one ray");
        this._numOfRays = _numOfRays;
    }

public void setBvh(boolean sign)
{
    this._scene.getGeometries().setBoxes=sign;
}

    /**
     * private class pixel- needed for threading
     */
    private class Pixel {
        private long _maxRows = 0;
        private long _maxCols = 0;
        private long _pixels = 0;
        public volatile int row = 0;
        public volatile int col = -1;
        private long _counter = 0;
        private int _percents = 0;
        private long _nextCounter = 0;

        /**
         * gets the next pixel and updates percentage done
         * @param target the target pixel
         * @return 0 if successful 1 if not
         */
        private synchronized int nextP(Pixel target) {
            ++col;
            ++_counter;
            if (col < _maxCols) {
                target.row = this.row;
                target.col = this.col;
                if (print && _counter == _nextCounter) {
                    ++_percents;
                    _nextCounter = _pixels * (_percents + 1) / 100;
                    return _percents;
                }
                return 0;
            }
            ++row;
            if (row < _maxRows) {
                col = 0;
                target.row = this.row;
                target.col = this.col;
                if (print && _counter == _nextCounter) {
                    ++_percents;
                    _nextCounter = _pixels * (_percents + 1) / 100;
                    return _percents;
                }
                return 0;
            }
            return -1;
        }

        /**
         * Public function for getting next pixel number into secondary Pixel object.
         * The function prints also progress percentage in the console window.
         *
         * @param target target secondary Pixel object to copy the row/column of the next pixel
         * @return true if the work still in progress, -1 if it's done
         */
        public boolean nextPixel(Pixel target) {
            int percents = nextP(target);
            if (percents > 0)
                if (Render.this.print) System.out.printf("\r %02d%%", percents);
            if (percents >= 0)
                return true;
            if (Render.this.print) System.out.printf("\r %02d%%", 100);
            return false;
        }
        public Pixel(int maxRows, int maxCols) {
            _maxRows = maxRows;
            _maxCols = maxCols;
            _pixels = maxRows * maxCols;
            _nextCounter = _pixels / 100;
            if (Render.this.print) System.out.printf("\r %02d%%", _percents);
        }
        public Pixel(){};
    }


    /**
     * checks if a shape is shaded or not
     * @param l - light direction
     * @param n - normal vector
     * @param gp - Geopoint
     * @return true if the shape isnt shaded, else false
     */
    private boolean unshaded(LightSource light,Vector l, Vector n, Intersectable.GeoPoint gp)
    {
        Vector lightDirection = l.scale(-1); // from point to light source - scale direction with negative one
        Vector delta ;
        double dotResult=n.dotProduct(lightDirection);//normal vector dot product the light direction
        if(dotResult>0)
            delta=n.scale(DELTA);
        else // if its negative we make it positive
            delta=n.scale(-DELTA);
        Point3D point = gp.point.add(delta);//adds the calculated delta to the geopoint
        Ray lightRay = new Ray(point, lightDirection,n);//creates a new ray from the point and the light direction and the nomral vector from geopoint
        List<Intersectable.GeoPoint> intersections = _scene.getGeometries().getfindIntersections(lightRay);
        if( intersections == null) return true;//checks if there are intersections with the light sources

        double lightDistance = light.getDistance(gp.point);//distance between the light source and geopoint
        for (Intersectable.GeoPoint g : intersections)//checks for each point if the distance is negative and the transparency is 0
        {
            if (alignZero(g.point.distance(gp.point)- lightDistance) <= 0 &&
                    g.getGeometry().get_material().get_kT()==0)
                return false;
        }
        return true;


    }

    /**
     * constructing a reflected ray  r=v-2*(v*n)*n
     * @param n Vector Normal
     * @param p Point3D of a geopoint
     * @param in Ray
     * @return the constructed reflected ray
     */
    private Ray constructReflectedRay(Vector n, Point3D p, Ray in)
    {

        Vector r= in.get_dir().subtract(n.scale(((in.get_dir().dotProduct(n))*2)));
        return new Ray(p,r,n);
    }

    /**
     * constructing a refracted(transparent) ray
     * @param p Point3D of geopoint
     * @param in Ray
     * @return the transparent ray
     */
    private Ray constructRefractedRay(Point3D p, Ray in,Vector n)
    {

        return new Ray(p,in.get_dir(),n);
    }
    /**
     * constructor for class render
     * @param imageWriter image writer
     * @param scene scene to write image for
     */
    public Render(ImageWriter imageWriter, Scene scene) {
        this._imageWriter = imageWriter;
        this._scene=   scene;
    }

    /**
     * finds intersectons between a ray geometries and reaturns the geopoint closest to the ray's p0
     * @param ray Ray that we are checking for intersections with
     * @return the geopoint closest to p0
     */
    private Intersectable.GeoPoint findClosestIntersection(Ray ray)
    {
        if(ray==null|| ray.get_dir().get_head()==Point3D.ZERO)//so there are no problems with the zero vector
        {
            return null;
        }
        Intersectable.GeoPoint closest=null;// so far the closest point is unknown
        double distance=Double.MAX_VALUE;
        double check;

       List<Intersectable.GeoPoint>intersections=_scene.getGeometries().bvhTree(ray);
        if(intersections==null)//if there are no intersection points
            return null;
        for(Intersectable.GeoPoint gp: intersections)//for each intersection point we check if it is closer to p0 than the previous pints
        {
            check=ray.get_p0().distance(gp.getPoint());//distance between p0 and the geopoint
            if(check <distance)//if the distance between the new point and p0 is smaller the previous smallest distance
            {
                distance=check;//updates the distance
                closest=gp;//updates the geopoint
            }
        }
        return closest;
    }


    /**
     * get scene function
     * @return scene
     */
    public Scene get_scene() {
        return _scene;
    }



    /**
     * Filling the buffer according to the geometries that are in the scene.
     * This function does not creating the picture file, but create the buffer pf pixels
     */
    public void renderImage() {
        Camera camera = _scene.getCamera();//camera
        Intersectable geometries = _scene.getGeometries();//geometries in the scene
        java.awt.Color background = _scene.getBackground().getColor();//the background color
        double distance = _scene.getDistance();//distance between view plane and camera

        int Nx = _imageWriter.getNx();
        int Ny = _imageWriter.getNy();
        double width = _imageWriter.getWidth();
        double height = _imageWriter.getHeight();
        final Pixel thePixel=new Pixel(Ny,Nx);
        Thread[] threads = new Thread[this._threads];
        for (int i = _threads - 1; i >= 0; --i)
        {

            threads[i] = new Thread(()->{
                Pixel pixel=new Pixel();
                while(thePixel.nextPixel(pixel)) {
                    Ray ray = camera.constructRayThroughPixel(Nx, Ny, pixel.col, pixel.row, distance, width, height);//makes a new camera ray
                    Intersectable.GeoPoint closest = findClosestIntersection(ray);//finds the closest point to the ray's p0
                    if (closest == null)//if there are no intersection points than that pixel is background
                    {

                        _imageWriter.writePixel(pixel.col, pixel.row, background);
                    } else {
                        java.awt.Color pixelColor = calcColor(closest, ray).getColor();//calculates the closest point's color
                        _imageWriter.writePixel(pixel.col, pixel.row, pixelColor);//writes the closest point to the image we are creating
                    }
                }
            });



        }
        for (Thread thread : threads) thread.start();

        // Wait for all threads to finish
        for (Thread thread : threads) try { thread.join(); } catch (Exception e) {}
        if (print) System.out.printf("\r100%%\n");
    }


    /**
     * Finding the closest point to the P0 of the camera.
     * @param  intersectionPoints list of points, the function should find from this list the closet point to P0 of the camera in the scene
     * @return  the closest point to the camera
     */

    private Intersectable.GeoPoint getClosestPoint(List<Intersectable.GeoPoint> intersectionPoints) {
        Intersectable.GeoPoint result = null;
        double smallestDistance = Double.MAX_VALUE;

        for (Intersectable.GeoPoint geo : intersectionPoints) //for each geopoint finds distance from camera starting point and checks if it is closer to the point we want than previous points
        {
            Point3D pt = geo.getPoint();
            double distance = this._scene.getCamera().get_p0().distance(pt);
            if (distance < smallestDistance) {
                smallestDistance = distance;
                result = geo;
            }
        }
        return result;
    }

    /**
     * Printing the grid with a fixed interval between lines
     * @param interval The interval between the lines.
     */
    public void printGrid(int interval,Color colorsep)
    {
        double rows = this._imageWriter.getNx();
        double columns = _imageWriter.getNy();
        for (int col = 0; col < columns; col++)
        {
            for (int row = 0; row < rows; row++)
            {
                if (col % interval == 0 || row % interval == 0)//if grid line needs to be printed
                {
                    _imageWriter.writePixel(row, col, colorsep);//writes the color grid that we want in the needed place
                }
            }
        }
    }

    /**
     * wrtie to image- calls image writer's write to image
     */
    public void writeToImage() {
        _imageWriter.writeToImage();
    }

    /**
     * recursion helper function for calcColor- Calculate the color intensity in a point
     * @param gp Geopoint  for which the color is required
     * @param ray Ray
     * @return the color intensity of the requested point
     */
    private primitives.Color calcColor(Intersectable.GeoPoint gp, Ray ray)
    {
        primitives.Color output=calcColor(gp,ray,MAX_CALC_COLOR_LEVEL,1.0);
        output.add(_scene.getAmbientLight().getIntensity());
        return output;
    }
    /**
     * Calculate the color intensity in a point
     * @param gp the point for which the color is required
     * @param in Ray
     * @param level - the recursion level
     * @param k - double - helps with recursion
     * @return the color intensity
     */
    private primitives.Color calcColor(Intersectable.GeoPoint gp,Ray in,int level,double k) {
        if (level == 1 || k < MIN_CALC_COLOR_K)
            return primitives.Color.BLACK;
        List<Ray>beam=new LinkedList<>();
        primitives.Color color = gp.getGeometry().get_emission();//the geometries emission light
        Vector v = gp.point.subtract(_scene.getCamera().get_p0()).normalize();//subtracts the camera starting point from the geopoint and normalizes the vector


        double kr = k * gp.getGeometry().get_material().get_kR();//reflection
        double kt = k * gp.getGeometry().get_material().get_kT();//refraction
        double transparencyAmount=0;//transparency
        for (LightSource lightSource : _scene.getLightSources())//for each light source in the scene's light sources
        {
            Vector l = lightSource.getL(gp.point);//the lights direction from geopoint
            if (alignZero( gp.geometry.getNormal(gp.getPoint()).dotProduct(l)) * alignZero( gp.geometry.getNormal(gp.getPoint()).dotProduct(v)) > 0)//if the dot product between the normal and the light direction times the dot product btween the normal and the normal vector between the camera and geopoint
            {
                //   if (unshaded(lightSource, l, n, gp))//if the geopoint isnt shaded by the light
                transparencyAmount = transparency(lightSource, l,  gp.geometry.getNormal(gp.getPoint()), gp);
                if (transparencyAmount * k > MIN_CALC_COLOR_K) {
                    Material material =gp.geometry.get_material();
                    double ln =l.dotProduct( gp.geometry.getNormal(gp.getPoint()));
                    color = color.add(calcDiffusive(
                                    material.getKd(),
                                    ln,
                                    lightSource.getIntensity(gp.getPoint())),
                            calcSpecular(
                                    material.getKs(), l,
                                    gp.geometry.getNormal(gp.getPoint()),
                                    ln,
                                    v,
                                    material.getnShininess(),
                                    lightSource.getIntensity(gp.getPoint())));
                }
            }
        }

        if (kr > MIN_CALC_COLOR_K)//if the reflection is bigger than the minimum of calc color
        {
           Ray reflection= constructReflectedRay(gp.getGeometry().getNormal(gp.getPoint()), gp.getPoint(), in);
            if(this._numOfRays==0 ||this._rayDistance<0)
                beam.add(reflection);
            else
                beam=  reflection.createBeamOfRays(gp.getGeometry().getNormal(gp.getPoint()),this.get_rayDistance(),this.get_numOfRays());
            primitives.Color tempColorReflection = primitives.Color.BLACK;
            for(Ray r :beam)
            {
                Intersectable.GeoPoint reflectedGp = findClosestIntersection(r);//find the closest point to the reflection ray's p0
                if (reflectedGp != null)//if such a point exists
                {
                    tempColorReflection = tempColorReflection.add(calcColor(reflectedGp, r, level - 1, kr).scale(kr));//calls the recursion th find the rest of the color and then scales it with the reflection
                }
            }
            color = color.add(tempColorReflection.reduce(beam.size()));


        }
        if (kt > MIN_CALC_COLOR_K)//if the refraction is bigger than the minimum of calc color
        {



            Ray refraction = constructRefractedRay(gp.getPoint(), in, gp.getGeometry().getNormal(gp.getPoint()));//constructs a refracted ray
            if(this._numOfRays==0 ||this._rayDistance<0)
                beam.add(refraction);
            else
                beam=  refraction.createBeamOfRays(gp.getGeometry().getNormal(gp.getPoint()),this.get_rayDistance(),this.get_numOfRays());
            primitives.Color tempColorRefraction = primitives.Color.BLACK;
            for(Ray r :beam) {
                Intersectable.GeoPoint refractedGp = findClosestIntersection(r);//find the closest point to the refracted ray's p0
                if (refractedGp != null)//if such a point exists
                {
                    tempColorRefraction = tempColorRefraction.add(calcColor(refractedGp, r, level - 1, kt).scale(kt));//calls the recursion to find the rest of the color and then scales it with the refracted

                }
            }
            color = color.add(tempColorRefraction.reduce(beam.size()));
        }

        return color;


    }
    /**
     * calculates transparency - featured in course slideshow
     * @param light - light source
     * @param l Vector
     * @param n Vector
     * @param gp Geopoint
     * @return double - the amount of transparency
     */
    private   double transparency(LightSource light, Vector l, Vector n, Intersectable.GeoPoint gp)
    {
        Vector direction=l.scale(-1);//direction from point to light source
        Ray ray=new Ray(gp.getPoint(),direction,n);//creates a new ray with the geopoint and normal received and the light direction
        List<Intersectable.GeoPoint>intersections=_scene.getGeometries().getfindIntersections(ray);//finds intersections between the scene's geometries and the new ray
        if(intersections==null)//if there are no intersections
            return 1.0;
        double distance=light.getDistance(gp.getPoint());//the distance between the light source and the geopoint
        double ktr=1.0;
        for(Intersectable.GeoPoint g:intersections)//for each intersection point checks if the distance is negative and updates the transparency accordingly
        {
            if (alignZero(g.getPoint().distance(gp.getPoint()) - distance) <= 0) {
                ktr *= g.getGeometry().get_material().get_kT();///multiplies with the geometry's transparency
                if (ktr < MIN_CALC_COLOR_K)//if the transparency is smaller than the min calc color
                {
                    return 0.0;
                }
            }
        }
        return ktr;
    }
    /**
     * checks if a value is positive
     * @param val - double
     * @return true if number is positive, else negative
     */
    private boolean sign(double val) {
        return (val > 0d);
    }

    /**
     * Calculate Specular component of light reflection.
     *
     * @param ks         specular component
     * @param l          direction from light
     * @param n          normal to surface
     * @param nl         dot product n*l
     * @param v          direction from point of view
     * @param nShininess shininess level
     * @param ip         light intensity
     * @return specular light color
     */
    private primitives.Color calcSpecular(double ks, Vector l, Vector n, double nl, Vector v, int nShininess, primitives.Color ip)
    {
        Vector R = l.add(n.scale(-2 * nl)); // nl cannot be zero
        double dotResult=R.dotProduct(v);
        double minusVR = -alignZero(dotResult);
        if (minusVR <= 0)
        {
            return primitives.Color.BLACK; // view from direction opposite to r vector
        }
        primitives.Color scaled=ip.scale(ks * Math.pow(minusVR, nShininess));//scales the light intensity with the specular component times minusVR to the power of nShininess
        return scaled;
    }

    /**
     * Calculate Diffusive component of light reflection.
     *
     * @param kd diffusive component
     * @param nl dot-product n*l
     * @param ip light intensity at the point
     * @return diffusive component of light reflection
     */
    private primitives.Color calcDiffusive(double kd, double nl, primitives.Color ip)
    {
        if (nl < 0) //if nl is negative we make it positive
            nl = Math.abs(nl);
        primitives.Color scaled=ip.scale(nl * kd);//scales nl with the diffuse component
        return scaled;
    }

}

