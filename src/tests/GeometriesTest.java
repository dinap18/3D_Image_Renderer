package tests;

import geometries.*;
import geometries.Polygon;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {
    /**
     * test method for  {@link geometries.Geometries#findIntersections(primitives.Ray)}.
     */
    @Test
    void findIntersections() {
        Geometries list=new Geometries();
        Ray r=new Ray(new Point3D(3,4,2),new Vector(1,2,5));



        // =============== Boundary Values Tests ==================
        //BVA empty list
        assertEquals(list.findIntersections(r),null);
        //BVA no intersections
          list.add(new Triangle(new Point3D (2,0,5),new Point3D (2,3,5),new Point3D (7,2,1)));
        list.add(new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0),
                new Point3D(-1, 1, 1)));
        list.add( new Sphere(4, new Point3D(0,0,0)));
        assertEquals(list.findIntersections(r),null);
        //BVA one intersection
        list.add(new Triangle(new Point3D(4,5,2),new Point3D(5,6,7),new Point3D(2,4,3)));
        assertEquals(list.findIntersections(r).size(),1);
        //BVA all intersect
        Geometries secondList=new Geometries();
        secondList.add(new Sphere(5,new Point3D(3,9,6)));
        secondList.add(new Sphere(6,new Point3D(3,5,3)));
        secondList.add(new Plane(new Point3D(3,4,4),new Vector(1,5,4)));
        secondList.add(new Triangle(new Point3D(4,5,2),new Point3D(5,6,7),new Point3D(2,4,3)));
        assertEquals(secondList.findIntersections(r).size(),5);
        // ============ Equivalence Partitions Tests ==============
        //EP some intersect
        list.add(secondList);
        assertEquals(list.findIntersections(r).size(),6);






    }
}