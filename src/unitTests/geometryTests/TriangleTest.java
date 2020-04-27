package unitTests.geometryTests;
import org.junit.jupiter.api.Test;
import primitives.*;
import geometries.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * triangle tests
 */
class TriangleTest {
    /**
     * test for triangle get normal function {@link geometries.Triangle#getNormal(primitives.Point3D)}.
     */
    @Test
            public void testNormal() {


        Point3D p1 = new Point3D(1, 5, 3);
        Point3D p2 = new Point3D(4, 5, 6);
        Point3D p3 = new Point3D(7, 8, 9);
        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);
        Triangle tri = new Triangle(p1, p2, p3);
        Vector v = v1.crossProduct(v2);
        Vector n=v.normalize();
        Vector scaled=n.scale(-1);
        Vector v4 = tri.getNormal(p1);
        assertTrue(scaled.equals( v4));
    }

    @Test
    /**
     * test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}.
     */
    void findIntersections() {
        // ============ Equivalence Partitions Tests ==============
        //EP inside triangle
        Point3D a= new Point3D(1.0,3.0,5.0);
        Point3D b= new Point3D(0,4,0);
        Point3D c= new Point3D(0,2,1);
        Triangle t=new Triangle(a,b,c);
        Point3D d=new Point3D(0.3441906873614191,3.1000665188470062,1.998824833702882);
        Ray r=new Ray(new Point3D(1.66,0.82,0),new Vector(new Point3D(-1.31,2.27,1.99)));
        assertEquals(t.findIntersections(r).get(0),(d));
        //Ep test outside against edge
        Ray f= new Ray(new Point3D(-0.61,0.07,-3.16),new Vector(-2.41,3.47,4.76));
        assertEquals(t.findIntersections(f),null);
        //EP test outside against vertex
        Ray e=new Ray(new Point3D(2.06,-0.91,0),new Vector(-4.07,3.65,0));
        assertEquals(t.findIntersections(e),null);

        // =============== Boundary Values Tests ==================

        //BVA in vertex//
        Ray w= new Ray(new Point3D(3.29,-1.55,0),new Vector(-3.29,3.55,1));
        assertEquals(t.findIntersections(w),null);
        //BVA on edge//
        Ray z=new Ray(new Point3D(2.45,-1.05,0 ),new Vector(-2.45,3.87,0.59));
    assertEquals(t.findIntersections(z),null);
    //BVA on edge's continuation//
        Ray k =new Ray (new Point3D(2.73,-1.62,0),new Vector(-2.73,5.62,-1.59) );
        assertEquals(t.findIntersections(k),null);
    }
}