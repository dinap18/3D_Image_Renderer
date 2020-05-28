package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

import primitives.*;

import static primitives.Util.alignZero;

/**
 * Class Sphere
 */
public class Sphere extends RadialGeometry
{
    /**
     *fields (center)
     */
    private final Point3D _center;

    /**
     * Constructor of Sphere
     * @param _radius
     * @param _center
     */
    public Sphere(double _radius, Point3D _center)
    {
        this(Color.BLACK,new Material(0,0,0),_radius,_center);
    }

    /**
     * Constructor for class sphere
     * @param emissionLight the emission light
     * @param material the sphere's material
     * @param radius the radius of the sphere
     * @param center the center of the sphere
     */
    public Sphere(Color emissionLight, Material material, double radius, Point3D center) {
        super(emissionLight, radius, material);
        this._center = new Point3D(center);
    }

    /**
     * Constructor for class sphere
     * @param emissionLight the emission light
     * @param radius the radius of the sphere
     * @param center the center of the sphere
     */
    public Sphere(Color emissionLight, double radius, Point3D center) {
        this(emissionLight,new Material(0,0,0),radius,center);
    }

    /**
     * GetNormal Function
     * @param p
     * @return null
     */
    @Override
    public Vector getNormal(Point3D p)
    {
        Vector orthogonal = new Vector(p.subtract(this._center));
        return orthogonal.normalize();
    }

    /**
     * GetCenter function
     * @return center
     */
    public Point3D get_center() {
        return _center;
    }

  /**
     * ToString function
     * @return string with the center 
     */
    @Override
    public String toString() {
        return super.toString()+
                " center" + _center ;
    }

    /**
     * finds intersections between a ray and a sphere
     * @param ray - to check if it intersects with the sphere
    * @param max - max distance (double)
     * @return the intersection point3Ds
     */
    @Override
    public  List<GeoPoint> findIntersections(Ray ray,double max) {
        Point3D p0 = ray.get_p0();//beginning point of ray
        Vector v = ray.get_dir();//direction of ray
        Vector u;
        try//checking if the center of the sphere and p0 are the same point
        {
            u = _center.subtract(p0);
        } catch (IllegalArgumentException e)//zero vector
        {
            return List.of(new GeoPoint(this, (ray.getTargetPoint(this._radius))));//geopoint with Point3D that is target point of the radius
        }
        double tm = alignZero(v.dotProduct(u));//direction of ray dot product vector u
        double dSquared;
        if (tm == 0)
            dSquared= u.lengthSquared() ;//the length of vector u squared
       else
        dSquared=u.lengthSquared() - tm * tm;//the length of vector u squared minus v dot product u squared
        double thSquared = alignZero(this._radius * this._radius - dSquared);

        if (thSquared <= 0)//if thsquared is smaller than or equal to zero there are no intersection points
            return null;

        double th = alignZero(Math.sqrt(thSquared));//square root if thSquared
        if (th == 0)//if the root if thsquared equals zero there are no intersection points
            return null;

        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);
        double distance1=alignZero(max-t1);
        double distance2=alignZero(max-t2);
        if (t1 <= 0 && t2 <= 0)//if they are both smaller of equal to zero there are no intersection points
            return null;
        if(v.get_head()==Point3D.ZERO)
            return null;
        if (t1 > 0 && t2 > 0 && distance1>0 &&distance2>0 && ray.get_dir().get_head()!=Point3D.ZERO)//if they are both bigger than zero and the distance is positive
        {
            return List.of(
                    new GeoPoint(this,(ray.getTargetPoint(t1)))//the target point with t1
                    ,new GeoPoint(this,(ray.getTargetPoint(t2))));//the target point with t2
        }
        if (t1 > 0 && distance1>0)//if only t1 is bigger than zero and the distance is positive
            return List.of(new GeoPoint(this,(ray.getTargetPoint(t1))));
        else if(t2>0 &&distance2>0)//if t2 is positive and the distance is positive
            return List.of(new GeoPoint(this,(ray.getTargetPoint(t2))));
        return null;//no intersection points
    }
}
