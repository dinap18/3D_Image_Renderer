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
    public Triangle(Color emissionLight, Material material, Point3D p1, Point3D p2, Point3D p3)
    {
        super(emissionLight,material,p1,p2,p3);//calls polygon constructor
    }

    /**
     * constructor for class triangle
     * @param emissionLight The emission light
     * @param p1  point 1
     * @param p2 point 2
     * @param p3 point3
     */
    public Triangle(Color emissionLight, Point3D p1, Point3D p2, Point3D p3)
    {
        super(emissionLight,p1, p2, p3);//calls polygon constructor
    }
    /**
     * finds intersections between a ray and a triangle
     * @param ray -the ray that we are checking to see if it intersects with the triangle
     * @return list of Point3Ds that intersect with the triangle
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        List<GeoPoint> planeIntersections = _plane.findIntersections(ray);//finds the intersections with the plane
        if (planeIntersections == null) //if there are no intersections with the plane then there is no intersection with the triangle
            return null;

        Point3D p0 = ray.get_p0();//beginning point of ray
        Vector v = ray.get_dir();//direction of ray

        Vector v1 = _vertices.get(0).subtract(p0);//p0 subtracted from first point in triangle
        Vector v2 = _vertices.get(1).subtract(p0);//p0 subtracted from second point in triangle
        Vector v3 = _vertices.get(2).subtract(p0);//p0 subtracted from third point in triangle
        Vector v4=v1.crossProduct(v2);// v1 cross product v2
        double s1 = v.dotProduct(v4);//direction cross product v4
        if (isZero(s1)) //if it equals zero there are no intersection points
            return null;
        Vector v5=v2.crossProduct(v3);
        double s2 = v.dotProduct(v5);
        if (isZero(s2))
            return null;
        Vector v6=v3.crossProduct(v1);
        double s3 = v.dotProduct(v6);
        if (isZero(s3))
            return null;

        if ((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0))//if all the dot product results are positive or if all of them are negative
        {

            List<GeoPoint> result = new LinkedList<>();
            for (GeoPoint geo : planeIntersections)//adds all the plane intersection points to the list of results
            {
                result.add(new GeoPoint(this, geo.getPoint()));
            }
            return result;
        }

        return null;//no intersection points
    }
}
