package geometries;

import primitives.*;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.isZero;

/**
 * Class Triangle
 */
public class Triangle extends Polygon {
    /**
     * Constructor of the triangle class
     * @param p1 point 1
     * @param p2 point2
     * @param p3 point3
     */
    public Triangle(Point3D p1, Point3D p2, Point3D p3)
    {
        super(p1,p2,p3);
    }

    /**
     * constructor for class triangle
     * @param emissionLight The emission light
     * @param material The material of the triangle
     * @param p1  point 1
     * @param p2 point 2
     * @param p3 point 3
     */
    public Triangle(Color emissionLight, Material material, Point3D p1, Point3D p2, Point3D p3) {
        super(emissionLight,material,p1,p2,p3);
    }

    /**
     * constructor for class triangle
     * @param emissionLight The emission light
     * @param p1  point 1
     * @param p2 point 2
     * @param p3 point3
     */
    public Triangle(Color emissionLight, Point3D p1, Point3D p2, Point3D p3) {
        super(emissionLight,p1, p2, p3);
    }
    /**
     * finds intersections between a ray and a triangle
     * @param ray -the ray that we are checking to see if it intersects with the triangle
     * @return list of Point3Ds that intersect with the triangle
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        List<GeoPoint> planeIntersections = _plane.findIntersections(ray);
        if (planeIntersections == null) return null;

        Point3D p0 = ray.get_p0();
        Vector v = ray.get_dir();

        Vector v1 = _vertices.get(0).subtract(p0);
        Vector v2 = _vertices.get(1).subtract(p0);
        Vector v3 = _vertices.get(2).subtract(p0);

        double s1 = v.dotProduct(v1.crossProduct(v2));
        if (isZero(s1)) return null;
        double s2 = v.dotProduct(v2.crossProduct(v3));
        if (isZero(s2)) return null;
        double s3 = v.dotProduct(v3.crossProduct(v1));
        if (isZero(s3)) return null;

        if ((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0)) {
            //for GeoPoint
            List<GeoPoint> result = new LinkedList<>();
            for (GeoPoint geo : planeIntersections) {
                result.add(new GeoPoint(this, geo.getPoint()));
            }
            return result;
        }

        return null;
    }
}
