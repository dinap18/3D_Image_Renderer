package geometries;

import primitives.Point3D;
import primitives.Ray;
import renderer.ImageWriter;

import java.util.LinkedList;
import java.util.List;

//public class BoundingBox
//{
//    public double x1 = Double.NEGATIVE_INFINITY;
//    public double  x2=Double.POSITIVE_INFINITY;
//    public double y1 = Double.NEGATIVE_INFINITY;
//    public double y2=Double.POSITIVE_INFINITY;
//    public double z1 = Double.NEGATIVE_INFINITY;
//    public double z2=Double.POSITIVE_INFINITY;
//
//    public BoundingBox(double x1, double y1, double x2, double y2, double z1, double z2) {
//        this.x1 = Math.min(x1, x2);
//        this.x2 = Math.max(x1, x2);
//        this.y1 = Math.min(y1, y2);
//        this.y2 = Math.max(y1, y2);
//        this.z1 = Math.min(z1, z2);
//        this.z2 = Math.max(z1, z2);
//    }
//
//    public BoundingBox(Point3D p1, Point3D p2) {
//        this(p1.get_x().get(), p1.get_y().get(), p2.get_x().get(), p2.get_y().get(), p1.get_z().get(), p2.get_z().get());
//
//    }
//
//    /**
//     * checks if a point3D is inside of a box
//     * @param p Point3D
//     * @return true if the point3D is inside the box, else false
//     */
//    public boolean contains(Point3D p) {
//        return (p.get_x().get() >= x1 && p.get_x().get() <= x2 && p.get_y().get() >= y1 && p.get_y().get() <= y2
//                && p.get_z().get() >= z1 && p.get_z().get() <= z2);
//    }
//
//    /**
//     * checks if two boxes overlap each other
//     * @param other Bounding Box
//     * @return true if the boxes overlap each other , else false
//     */
//    public boolean overlaps( Intersectable.BoundingBox other)
//    {
//        return x2 > other.x1 &&
//                x1 < other.x2 &&
//                y2 > other.y1 &&
//                y1 < other.y2 &&
//                z2 > other.z1 &&
//                z1 < other.z2;
//    }
//
//    /**
//     * checks if there is an intersection between a box and a ray
//  //   * @param r
//     * @return
//     */
////    public BoundingBox buildBox(Intersectable g) {
////        if (g == null) {
////            return null;
////        }
////
////
////        if (g.getClass() == Sphere.class) {
////
////            double x1 = ((Sphere) g).get_center().get_x().get() - ((Sphere) g).get_radius();
////            double x2 = ((Sphere) g).get_center().get_x().get() + ((Sphere) g).get_radius();
////            double y1 = ((Sphere) g).get_center().get_y().get() - ((Sphere) g).get_radius();
////            double y2 = ((Sphere) g).get_center().get_y().get() + ((Sphere) g).get_radius();
////            double z1 = ((Sphere) g).get_center().get_z().get() - ((Sphere) g).get_radius();
////            double z2 = ((Sphere) g).get_center().get_z().get() + ((Sphere) g).get_radius();
////        } else if (g.getClass() == Polygon.class)
////        {
////
////                x1 = ((Polygon) g).get_vertices().get(0).get_x().get();
////                x2 = ((Polygon) g).get_vertices().get(0).get_x().get();
////                y1 = ((Polygon) g).get_vertices().get(0).get_y().get();
////                y2 = ((Polygon) g).get_vertices().get(0).get_y().get();
////                z1 = ((Polygon) g).get_vertices().get(0).get_z().get();
////                z2 = ((Polygon) g).get_vertices().get(0).get_z().get();
////
////                for (int i = 1; i < ((Polygon) g).get_vertices().size(); i++) {
////                    if (((Polygon) g).get_vertices().get(i).get_x().get() < x1)
////                        x1 = ((Polygon) g).get_vertices().get(i).get_x().get();
////                    if (((Polygon) g).get_vertices().get(i).get_y().get() < y1)
////                        y1 = ((Polygon) g).get_vertices().get(i).get_y().get();
////                    if (((Polygon) g).get_vertices().get(i).get_z().get() < z1)
////                        z1 = ((Polygon) g).get_vertices().get(i).get_z().get();
////                    if (((Polygon) g).get_vertices().get(i).get_x().get() > x2)
////                        x2 = ((Polygon) g).get_vertices().get(i).get_x().get();
////                    if (((Polygon) g).get_vertices().get(i).get_y().get() > y2)
////                        y2 = ((Polygon) g).get_vertices().get(i).get_y().get();
////                    if (((Polygon) g).get_vertices().get(i).get_z().get() > z2)
////                        z2 = ((Polygon) g).get_vertices().get(i).get_z().get();
////                }
////            }
////
////
////        else if(g.getClass()==Plane.class) // infinite shape that can't be boxed
////        {
////            double negative=Double.NEGATIVE_INFINITY;
////            double positive=Double.POSITIVE_INFINITY;
////           return new BoundingBox(negative,positive,negative,positive,negative,positive);
////        }
////
////        return new BoundingBox(x1,x2,y1,y2,z1,z2);
////    }
//public BoundingBox mergeBoxes(BoundingBox box)
//        {
//        return new BoundingBox(
//        Math.min(x1,box.x1),Math.max(x2,box.x2),
//        Math.min(y1,box.y1),Math.max(y2,box.y2),
//        Math.min(z1,box.z1),Math.max(z2,box.z2)
//        );
//        }
//public double findArea()
//        {
//        double a=x2-x1;
//        double b=y2-y1;
//        double c=z2-z1;
//        return (2*a*b)+(2*b*c)+(2*a*c);
//        }
//public Point3D getCenter()
//        {
//        return new Point3D (x2+x1/2,y2+y1/2,z2+z1/2);
//        }
//        }
//
//
//
//
//public boolean intersects(Ray r) {
//        double tmin = (x1 - r.get_p0().get_x().get()) / r.get_dir().get_head().get_x().get();
//        double tmax = (x2 - r.get_p0().get_x().get()) / r.get_dir().get_head().get_x().get();
//        double temp;
//        if (tmin > tmax) {
//        temp=tmin;
//        tmin=tmax;
//        tmax=temp;
//        }
//
//        double tymin = (y1 - r.get_p0().get_y().get()) / r.get_dir().get_head().get_y().get();
//        double tymax = (y2- r.get_p0().get_y().get()) / r.get_dir().get_head().get_y().get();
//
//        if (tymin > tymax)
//        {
//        temp=tymin;
//        tymin=tymax;
//        tymax=temp;
//        }
//        if ((tmin > tymax) || (tymin > tmax))
//        return false;
//
//        if (tymin > tmin)
//        tmin = tymin;
//
//        if (tymax < tmax)
//        tmax = tymax;
//
//        double tzmin = (z1 - r.get_p0().get_z().get()) / r.get_dir().get_head().get_z().get();
//        double tzmax = (z2 - r.get_p0().get_z().get()) / r.get_dir().get_head().get_z().get();
//
//        if (tzmin > tzmax)
//        {   temp=tzmin;
//        tzmin=tzmax;
//        tzmax=temp;
//        }
//
//        if ((tmin > tzmax) || (tzmin > tmax))
//        return false;
//
//
//        return true;
//
//        }
//        }
//public Geometries bvh(Geometries... shapes )
//        {
//        Geometries geometriesList=null;
//        for(Geometries shape: shapes)
//        geometriesList.add(shape);
//
//        Geometries clusters=null;
//        for(Geometries shape: shapes)
//        clusters.add(shape);
//
//        Geometries left=null;
//        Geometries right=null;
//
//        double best;
//        while(geometriesList.get_geometries().size() > 1)
//        {
//        best = Double.POSITIVE_INFINITY;
//        for (Geometries g : shapes)
//        {
//        for (Geometries j : shapes) {
//        if (g != j) {
//        BoundingBox gBox = g.buildBox();
//        BoundingBox jBox = j.buildBox();
//        BoundingBox merged = gBox.mergeBoxes(jBox);
//        double area = merged.findArea();
//        if (area < best) {
//        best = area;
//        left = g;
//        right = j;
//        }
//        }
//        }
//
//        }
//        Geometries clusterTag=null ;
//        clusterTag.add(left);
//        clusterTag.add(right);
//        clusters.addAll(geometriesList);
//        clusters.remove(left);
//        clusters.remove(right);
//        clusters.addAll(clusterTag);
//        }
//
//
//        return clusters;
//        }