package geometries;
import primitives.*;

import java.util.*;

public class Bvh extends Geometries {



//
//    boolean intersect( Ray direction ) {
//        double theta=0.0;
//
//
//
//        if (direction.get_dir().get_head().get_x().get() > 0.0 )
//            theta = Math.max( theta, (lower.x - origin.x)/direction.x );
//        if (direction.x < 0.0 )
//            theta = Math.max( theta, (upper.x - origin.x)/direction.x );
//        if (direction.y > 0.0 )
//            theta = Math.max( theta, (lower.y - origin.y)/direction.y );
//        if (direction.y < 0.0 )
//            theta = Math.max( theta, (upper.y - origin.y)/direction.y );
//        if (direction.z > 0.0 )
//            theta = Math.max( theta, (lower.z - origin.z)/direction.z );
//        if (direction.z < 0.0 )
//            theta = Math.max( theta, (upper.z - origin.z)/direction.z );
//
//        intersect.x = origin.x + theta*direction.x;
//        intersect.y = origin.y + theta*direction.y;
//        intersect.z = origin.z + theta*direction.z;
//
//        if (intersect.x < (lower.x-EPS)) return false;
//        if (intersect.x > (upper.x+EPS)) return false;
//        if (intersect.y < (lower.y-EPS)) return false;
//        if (intersect.y > (upper.y+EPS)) return false;
//        if (intersect.z < (lower.z-EPS)) return false;
//        if (intersect.z > (upper.z+EPS)) return false;
//
//        return true;
//
//    }
//    public Geometries bvh(Geometries... shapes )
//    {
//        Geometries geometriesList=null;
//        for(Geometries shape: shapes)
//            geometriesList.add(shape);
//
//        Geometries clusters=null;
//        for(Geometries shape: shapes)
//            clusters.add(shape);
//
//        Geometries left=null;
//        Geometries right=null;
//
//        double best;
//        while(geometriesList.get_geometries().size() > 1)
//        {
//            best = Double.POSITIVE_INFINITY;
//            for (Geometries g : shapes)
//            {
//                for (Geometries j : shapes) {
//                    if (g != j) {
//                        BoundingBox gBox = g.buildBox();
//                        BoundingBox jBox = j.buildBox();
//                        BoundingBox merged = gBox.mergeBoxes(jBox);
//                        double area = merged.findArea();
//                        if (area < best) {
//                            best = area;
//                            left = g;
//                            right = j;
//                        }
//                    }
//                }
//
//            }
//            Geometries clusterTag=null ;
//            clusterTag.add(left);
//            clusterTag.add(right);
//            clusters.addAll(geometriesList);
//            clusters.remove(left);
//            clusters.remove(right);
//            clusters.addAll(clusterTag);
//        }
//
//
//        return clusters;
//    }


//public List<Geometries> aac(List<Geometries>shapes)
//{
//    List<Point3D> centers=new LinkedList<>() ;
//
//    for(Geometries i :shapes)
//    {
//        BoundingBox b=i.buildBox();
//        centers.add(b.getCenter());
//    }
//    List<Geometries> c=buildTree(shapes);
//    return c;
//}
//public List<Geometries> buildTree(List<Geometries>shapes)
//{
//    List <Geometries> c=new LinkedList<Geometries>();
//    if(shapes.size()<4)
//    {
//        c.addAll(shapes);
//        return combineClusters(c,delta);
//    }
//    //pl,pr=make partion(shapes)
//    //c=buildtree(pl) add buildtree(pr)
//    return combineClusters(c,shapes.size());
//}
//    public List<Geometries> combineClusters(List<Geometries>shapes,double d)
//    {
//        Geometries left=null;
//        Geometries right=null;
//        List<Geometries> clusters=shapes;
//        List<Geometries>clusterTag=new LinkedList<Geometries>() ;
//        for(Geometries g: shapes)
//        {
//          //  g.closest = findbestmatch(g);
//        }
//        while(shapes.size()>d)
//        {
//            double best =Double.POSITIVE_INFINITY;
//            for(Geometries g: shapes)
//            {
//                BoundingBox gBox = g.buildBox();
//                BoundingBox jBox = g.closest.buildBox();
//                BoundingBox merged = gBox.mergeBoxes(jBox);
//                double area = merged.findArea();
//                if (area < best) {
//                    best = area;
//                    left = g;
//                    right = g.closest;
//                }
//            }
//            clusterTag.add(left);
//            clusterTag.add(right);
//            clusters.remove(left);
//            clusters.remove(right);
//            clusters.addAll(clusterTag);
//            clusters.
//        }
//    }
}
