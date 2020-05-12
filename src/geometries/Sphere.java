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
     * @return the intersection point3Ds
     */
    @Override
    public  List<GeoPoint> findIntersections(Ray ray) {
        Point3D p0 = ray.get_p0();
        Vector v = ray.get_dir();
        Vector u;
        try {
            u = _center.subtract(p0);   // p0 == _center
        } catch (IllegalArgumentException e) {
            return List.of(new GeoPoint(this, (ray.getTargetPoint(this._radius))));
        }
        double tm = alignZero(v.dotProduct(u));
        double dSquared = (tm == 0) ? u.lengthSquared() : u.lengthSquared() - tm * tm;
        double thSquared = alignZero(this._radius * this._radius - dSquared);

        if (thSquared <= 0) return null;

        double th = alignZero(Math.sqrt(thSquared));
        if (th == 0) return null;

        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);
        if (t1 <= 0 && t2 <= 0) return null;
        if (t1 > 0 && t2 > 0) {
            return List.of(
                    new GeoPoint(this,(ray.getTargetPoint(t1)))
                    ,new GeoPoint(this,(ray.getTargetPoint(t2)))); //P1 , P2
        }
        if (t1 > 0)
            return List.of(new GeoPoint(this,(ray.getTargetPoint(t1))));
        else
            return List.of(new GeoPoint(this,(ray.getTargetPoint(t2))));
    }
}
