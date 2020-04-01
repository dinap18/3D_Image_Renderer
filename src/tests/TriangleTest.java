package tests;
import org.junit.jupiter.api.Test;
import primitives.*;
import geometries.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * triangle tests
 */
class TriangleTest {
    /**
     * test for triangle get normal function @link geometries.Triangle# getNormal(primitives.Point3D)}.
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
}