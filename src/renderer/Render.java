package renderer;

import elements.AmbientLight;
import elements.Camera;
import elements.LightSource;
import geometries.*;
import primitives.*;

import scene.Scene;

import java.awt.Color;
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
     * checks if a shape is shaded or not
     * @param l - light direction from geopoint
     * @param n - normal vector from geopoint
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
        Ray lightRay = new Ray(point, lightDirection);//creates a new ray from the point and the light direction
        List<Intersectable.GeoPoint> intersections = _scene.getGeometries().findIntersections(lightRay);
        if( intersections == null) return true;//checks if there are intersections with the light sources
       return false;
    //return counter == 0;
    }
   /* public Render(Scene _scene,ImageWriter _imageWriter) {
        this._scene = _scene;
        this._imageWriter=_imageWriter;
    }*/

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

        for (int row = 0; row < Ny; row++) {
            for (int column = 0; column < Nx; column++) {
                Ray ray = camera.constructRayThroughPixel(Nx, Ny, column, row, distance, width, height);//makes a new camera ray
                List<Intersectable.GeoPoint> intersectionPoints = geometries.findIntersections(ray);//checks geometries for intersections
                if (intersectionPoints == null)//if there are no intersection points than that pixel is background
                    {
                    _imageWriter.writePixel(column, row, background);
                } else
                    {
                    Intersectable.GeoPoint closestPoint = getClosestPoint(intersectionPoints);//closest point for intersection points
                    java.awt.Color pixelColor = calcColor(closestPoint).getColor();//calculates the closest points color
                    _imageWriter.writePixel(column, row, pixelColor);//writes the closest point to the image
                }
            }
        }
    }

    /**
     * Finding the closest point to the P0 of the camera.
     * @param  intersectionPoints list of points, the function should find from this list the closet point to P0 of the camera in the scene
     * @return  the closest point to the camera
     */

    private Intersectable.GeoPoint getClosestPoint(List<Intersectable.GeoPoint> intersectionPoints) {
        Intersectable.GeoPoint result = null;
        double mindist = Double.MAX_VALUE;

        Point3D p0 = this._scene.getCamera().get_p0();//camera starting point

        for (Intersectable.GeoPoint geo : intersectionPoints) //for each geopoint finds distance from camera starting point and checks if it is closer to the point we want than previous points
        {
            Point3D pt = geo.getPoint();
            double distance = p0.distance(pt);
            if (distance < mindist) {
                mindist = distance;
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
     * Calculate the color intensity in a point
     * @param gp the point for which the color is required
     * @return the color intensity
     */
    private primitives.Color calcColor(Intersectable.GeoPoint gp) {
        primitives.Color color = _scene.getAmbientLight().getIntensity();//the intensity of the scene's ambient light
        color = color.add(gp.geometry.get_emission());//adds the geometries emission color to color
        Vector v = gp.point.subtract(_scene.getCamera().get_p0()).normalize();//subtracts the camera starting point from the geopoint and normalizes the vector
        Vector n = gp.geometry.getNormal(gp.getPoint());//gets normal vector from geopoint's point3D
        int nShininess = gp.geometry.get_material().getnShininess();//the geopoint's shininess
        double kd = gp.geometry.get_material().getKd();//geopoint diffuse
        double ks = gp.geometry.get_material().getKs();//geopoint specular
        for (LightSource lightSource : _scene.getLightSources())//for each light source in the scene's light sources
        {
            Vector l = lightSource.getL(gp.point);//the lights direction from geopoint
            if (n.dotProduct(l)*n.dotProduct(v) > 0)//if the dot proudct between the normal and the light direction times the dot product btween the normal and the normal vector between the camera and geopoint
                if (unshaded(lightSource,l, n, gp))//if the geopoint isnt shaded by the light
            {
                primitives.Color lightIntensity = lightSource.getIntensity(gp.point);//intensity color of the geopoint
                color = color.add(calcDiffusive(kd,l.dotProduct(n), lightIntensity),
                        calcSpecular(ks,l,n, l.dotProduct(n), v, nShininess, lightIntensity));//adds the specular and diffuse lights to the color
            }
        }
        return color;
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
     * @param nl         dot-product n*l
     * @param v          direction from point of view
     * @param nShininess shininess level
     * @param ip         light intensity
     * @return specular component light effect
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

