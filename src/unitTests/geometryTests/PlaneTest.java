package unitTests.geometryTests;
import primitives.*;
import geometries.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * plane tests
 */
class PlaneTest {
    /**
     * test for plane get normal function @link geometries.Plane#getNormal(primitves.Point3D)}.
     */
    @Test
    void getNormal() {
        Point3D point=new Point3D(3, 2,0);
        Point3D vectorPoint=new Point3D(0,0,1);
        Vector v=new Vector(vectorPoint);
        Plane p=new Plane(point,v);
        assertEquals(p.getNormal(point),v);
    }

    /**
     * test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
     */
    @Test
    void findIntersections() {

        Point3D a= new Point3D(-2,0,0);
        Point3D b= new Point3D(0,0,3);
        Point3D c= new Point3D(2,0,0);
        Plane p= new Plane(a,b,c);
        // ============ Equivalence Partitions Tests ==============
        //EP doesnt intersect plane
        Ray r= new Ray(new Point3D(0,0,3),new Vector(0,-2,-3));
        assertEquals(p.findIntersections(r),null);
        //EP intersects plane
        Ray e= new Ray(new Point3D(4,2,0),new Vector(-10,-2,6));
        assertEquals(p.findIntersections(e).get(0).getPoint(),new Point3D(-6,0,6));

        // =============== Boundary Values Tests ==================

        //BVA ray is parallel and included
        Ray f= new Ray(new Point3D(0,0,0),new Vector(0,0,2.44));
        assertEquals(p.findIntersections(f),null);
        //BVA ray is parallel and isnt included
        Ray g=new Ray(new Point3D(2,-2,0),new Vector(-4,0,0));
        assertEquals(p.findIntersections(g),null);
        //BVA ray is orthogonal before
        Ray k=new Ray(new Point3D(-1.75,1.19,0),new Vector(-0.05,3.3,0));
        assertEquals(p.findIntersections(k),null);
        //BVA ray is orthogonal in
        Ray q=new Ray(new Point3D(-2,0,0),new Vector(-0.11,-2.26,0));
        assertEquals(p.findIntersections(q),null);
        //BVA ray is orthogonal after
        Ray o=new Ray(new Point3D(-2.09,-0.88,0),new Vector(-0.07,-1.73,0));
        assertEquals(p.findIntersections(o),null);
        //BVA Ray is neither orthogonal nor parallel to and begins at the plane
        Ray h=new Ray(new Point3D(0,0,6),new Vector(-2.45,-2.08,-6));
        assertEquals(p.findIntersections(h),null);
        //BVA Ray is neither orthogonal nor parallel to the plane and begins in
        //the same point which appears as reference point in the plane
        Ray v=new Ray(new Point3D(-2,0,0),new Vector(3.45,0,2));
        assertEquals(p.findIntersections(v),null);

    }
}