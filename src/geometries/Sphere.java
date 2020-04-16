package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;

/**
 * Class Sphere
 */
public class Sphere extends RadialGeometry
{
    /**
     *fields (center)
     */
    Point3D _center;

    /**
     * Constructor of Sphere
     * @param _radius
     * @param _center
     */
    public Sphere(double _radius, Point3D _center)
    {
        super(_radius);
        this._center = _center;
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
        return orthogonal.normalized();
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
    public  List<Point3D> findIntersections(Ray ray) {
        Point3D p = ray.get_p0();
        Vector v = ray.get_dir();
        Vector u;
        try {
            u = _center.subtract(p);
        } catch (IllegalArgumentException e)// if the vector is the zero vector
        {
          return List.of(ray.getTargetPoint(_radius));
        }
        double dotProduct=v.dotProduct(u);
        double tm = Util.alignZero(dotProduct);
        double dSquared;
        if(tm==0)
        {
            dSquared=u.lengthSquared();
        }
        else {
            dSquared=u.lengthSquared() - tm * tm;
        }
        double thSquared = Util.alignZero(_radius * _radius - dSquared);

        if (thSquared <= 0) return null;

        double th = Util.alignZero(Math.sqrt(thSquared));
        if (th == 0) return null;

        double t1 = Util.alignZero(tm - th);
        double t2 = Util.alignZero(tm + th);
        if (t1 <= 0 && t2 <= 0) return null;
        if (t1 > 0 && t2 > 0) return List.of(ray.getTargetPoint(t1), ray.getTargetPoint(t2)); //P1 , P2
        if (t1 > 0)
            return List.of(ray.getTargetPoint(t1));
        else
            return List.of(ray.getTargetPoint(t2));
    }
}
