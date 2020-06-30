package geometries;
import primitives.*;

import java.util.List;
import java.util.Objects;

/**
 *  abstract class that contains list of intersection points
 */
public abstract class Intersectable {

    /**
     * parameter for using BVH feature
     */
    public boolean setBoxes = false;


    /**
     * inside helper class Bounding Box
     */
    public class BoundingBox {
        // 1 is the min value of the coordinate and 2 is the max , we start them at + - infinity
        public double x1 = Double.NEGATIVE_INFINITY;
        public double x2 = Double.POSITIVE_INFINITY;
        public double y1 = Double.NEGATIVE_INFINITY;
        public double y2 = Double.POSITIVE_INFINITY;
        public double z1 = Double.NEGATIVE_INFINITY;
        public double z2 = Double.POSITIVE_INFINITY;

    }

    //calls the default constructor to build a box for each intersectable shape
    protected BoundingBox box = new BoundingBox();

    /**
     * checks if a ray intersects the box around a geometry
     * @param r the ray we are checking for an intersection with
     * @return
     */
    public boolean intersects(Ray r) {
        double xP=r.get_p0().get_x().get();
        double xD=r.get_dir().get_head().get_x().get();
        double yP=r.get_p0().get_y().get();
        double yD=r.get_dir().get_head().get_y().get();
        double zP=r.get_p0().get_z().get();
        double zD=r.get_dir().get_head().get_z().get();
        double tmin = (box.x1 - xP / xD); //starting min x
        double tmax = (box.x2 - xP /xD);//starting max x
        double temp;
        if (tmin > tmax) // if min is bigger we need to swap them so max will have the bigger value
        {
            temp = tmin;
            tmin = tmax;
            tmax = temp;
        }

        double tymin = (box.y1 - yP / yD);//min y
        double tymax = (box.y2 - yP/ yD);//min x

        if (tymin > tymax)  // if min is bigger we need to swap them so max will have the bigger value
        {
            temp = tymin;
            tymin = tymax;
            tymax = temp;
        }
        if ((tmin > tymax) || (tymin > tmax)) // if min x is bigger than max y or min y is bigger than max x there cant be an intersection with the box
            return false;

        if (tymin > tmin) //if y min is bigger than x min then it takes its place
            tmin = tymin;

        if (tymax < tmax)//if y max is bigger than x max then it takes its place
            tmax = tymax;

        double tzmin = (box.z1 - zP / zD);//z min
        double tzmax = (box.z2 - zP / zD); //z max

        if (tzmin > tzmax) // if min is bigger we need to swap them so max will have the bigger value
        {
            temp = tzmin;
            tzmin = tzmax;
            tzmax = temp;
        }

        if ((tmin > tzmax) || (tzmin > tmax)) // if min  is bigger than max z or min z is bigger than max  there cant be an intersection with the box
            return false;


        return true; // if the ray intersects the box

    }



    /**
     * find intersection function
     *
     * @param ray Ray
     * @return list of geoPoint intersections between a shape and a ray
     */
    public List<GeoPoint> getfindIntersections(Ray ray) {
        if (!setBoxes || intersects(ray))
            return findIntersections(ray);
        return null;
    }
    abstract List<GeoPoint>findIntersections(Ray ray);

    /**
     * static helper class geoPoint
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point3D point;

        /**
         * get geometry function
         *
         * @return geometry
         */
        public Geometry getGeometry() {
            return geometry;
        }

        /**
         * get Point function
         *
         * @return Point3D
         */
        public Point3D getPoint() {
            return point;
        }

        /**
         * constructor for static helper class geoPoint
         *
         * @param geometry geometric shape
         * @param point    Point3D point
         */
        public GeoPoint(Geometry geometry, Point3D point) {
            this.geometry = geometry;
            this.point = point;
        }

        /**
         * equals function for static helper class intersectable
         *
         * @param o geoPoint
         * @return if the geoPoints are equal
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return Objects.equals(geometry, geoPoint.geometry) &&
                    Objects.equals(point, geoPoint.point);
        }


    }
}