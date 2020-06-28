package geometries;
import primitives.*;

import java.util.List;
import java.util.Objects;

/**
 *  interface that contains list of intersection points
 */
public abstract class Intersectable {

    public boolean setBoxes = false;





    public class BoundingBox {
        public double x1 = Double.NEGATIVE_INFINITY;
        public double x2 = Double.POSITIVE_INFINITY;
        public double y1 = Double.NEGATIVE_INFINITY;
        public double y2 = Double.POSITIVE_INFINITY;
        public double z1 = Double.NEGATIVE_INFINITY;
        public double z2 = Double.POSITIVE_INFINITY;

    }


    protected BoundingBox box = new BoundingBox();

    public boolean intersects(Ray r) {
//        double tmin = (box.x1 - r.get_p0().get_x().get()) / r.get_dir().get_head().get_x().get();
//        double tmax = (box.x2 - r.get_p0().get_x().get()) / r.get_dir().get_head().get_x().get();
//        double temp;
//        if (tmin > tmax) {
//            temp = tmin;
//            tmin = tmax;
//            tmax = temp;
//        }
//
//        double tymin = (box.y1 - r.get_p0().get_y().get()) / r.get_dir().get_head().get_y().get();
//        double tymax = (box.y2 - r.get_p0().get_y().get()) / r.get_dir().get_head().get_y().get();
//
//        if (tymin > tymax) {
//            temp = tymin;
//            tymin = tymax;
//            tymax = temp;
//        }
//        if ((tmin > tymax) || (tymin > tmax))
//            return false;
//
//        if (tymin > tmin)
//            tmin = tymin;
//
//        if (tymax < tmax)
//            tmax = tymax;
//
//        double tzmin = (box.z1 - r.get_p0().get_z().get()) / r.get_dir().get_head().get_z().get();
//        double tzmax = (box.z2 - r.get_p0().get_z().get()) / r.get_dir().get_head().get_z().get();
//
//        if (tzmin > tzmax) {
//            temp = tzmin;
//            tzmin = tzmax;
//            tzmax = temp;
//        }
//
//        if ((tmin > tzmax) || (tzmin > tmax))
//            return false;
//
//
//        return true;
        Point3D start = r.get_p0();

        double start_X = start.get_x().get();
        double start_Y = start.get_y().get();
        double start_Z = start.get_z().get();

        Point3D direction = r.get_dir().get_head();

        double direction_X = direction.get_x().get();
        double direction_Y = direction.get_y().get();
        double direction_Z = direction.get_z().get();

        double max_t_for_X;
        double min_t_for_X;

        //If the direction_X is negative then the _min_X give the maximal value
        if (direction_X < 0) {
            max_t_for_X = (box.x1 - start_X) / direction_X;
            // Check if the Intersectble is behind the camera
            if (max_t_for_X <= 0) return false;
            min_t_for_X = (box.x2 - start_X) / direction_X;
        }
        else if (direction_X > 0) {
            max_t_for_X = (box.x2 - start_X) / direction_X;
            if (max_t_for_X <= 0) return false;
            min_t_for_X = (box.x1 - start_X) / direction_X;
        }
        else {
            if (start_X >= box.x1 || start_X <= box.x1)
                return false;
            else{
                max_t_for_X = Double.POSITIVE_INFINITY;
                min_t_for_X = Double.NEGATIVE_INFINITY;
            }
        }

        double max_t_for_Y;
        double min_t_for_Y;

        if (direction_Y < 0) {
            max_t_for_Y = (box.y1 - start_Y) / direction_Y;
            if (max_t_for_Y <= 0) return false;
            min_t_for_Y = (box.y2 - start_Y) / direction_Y;
        }
        else if (direction_Y > 0) {
            max_t_for_Y = (box.y2 - start_Y) / direction_Y;
            if (max_t_for_Y <= 0) return false;
            min_t_for_Y = (box.y1 - start_Y) / direction_Y;
        }
        else {
            if (start_Y >= box.y2 || start_Y <= box.y1)
                return false;
            else{
                max_t_for_Y = Double.POSITIVE_INFINITY;
                min_t_for_Y = Double.NEGATIVE_INFINITY;
            }
        }

        //Check the maximal and the minimal value for t
        double temp_max = Math.min(max_t_for_Y,max_t_for_X);
        double temp_min = Math.max(min_t_for_Y,min_t_for_X);
        temp_min = Math.max(temp_min,0);

        if (temp_max < temp_min) return false;

        double max_t_for_Z;
        double min_t_for_Z;

        if (direction_Z < 0) {
            max_t_for_Z = (box.z1 - start_Z) / direction_Z;
            if (max_t_for_Z <= 0) return false;
            min_t_for_Z = (box.z2 - start_Z) / direction_Z;
        }
        else if (direction_Z > 0) {
            max_t_for_Z = (box.z2 - start_Z) / direction_Z;
            if (max_t_for_Z <= 0) return false;
            min_t_for_Z = (box.z2 - start_Z) / direction_Z;
        }
        else {
            if (start_Z >= box.z2 || start_Z <= box.z1)
                return false;
            else{
                max_t_for_Z = Double.POSITIVE_INFINITY;
                min_t_for_Z = Double.NEGATIVE_INFINITY;
            }
        }

        temp_max = Math.min(max_t_for_Z,temp_max);
        temp_min = Math.max(min_t_for_Z,temp_min);

        if (temp_max < temp_min) return false;

        return true;

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