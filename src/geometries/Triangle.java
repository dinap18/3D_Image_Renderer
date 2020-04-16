package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

/**
 * Class Triangle
 */
public class Triangle extends Polygon {
    Point3D _p1;
    Point3D _p2;
    Point3D _p3;
    /**
     * Constructor of the triangle class
     * @param p1
     * @param p2
     * @param p3
     */
    public Triangle(Point3D p1, Point3D p2, Point3D p3)
    {
        super(p1,p2,p3);
    }

    /**
     * finds intersections between a ray and a triangle
     * @param ray -the ray that we are checking to see if it intersects with the triangle
     * @return list of Point3Ds that intersect with the triangle
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> intersections = _plane.findIntersections(ray);
        if (intersections == null)
            return null;

        Point3D p = ray.get_p0();
        Vector v = ray.get_dir();

        Vector v1 = _vertices.get(0).subtract(p);
        Vector v2 = _vertices.get(1).subtract(p);
        Vector v3 = _vertices.get(2).subtract(p);
        Vector crossProduct1=v1.crossProduct(v2);
        double s1 = v.dotProduct(crossProduct1);
        if (Util.isZero(s1))
            return null;
        Vector crossProduct2=v2.crossProduct(v3);
        double s2 = v.dotProduct(crossProduct2);
        if (Util.isZero(s2))
            return null;
        Vector crossProduct3=v3.crossProduct(v1);
        double s3 = v.dotProduct(crossProduct3);
        if (Util.isZero(s3))
            return null;
       if((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0))
             return   intersections ;
       else
             return null;
    }
}
