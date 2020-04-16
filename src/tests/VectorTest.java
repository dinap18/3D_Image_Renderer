package tests;

import org.junit.jupiter.api.Test;
import primitives.*;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

/**
 * vector tests
 */
class VectorTest {


    /**
     * test for vector subtraction function {@link primitives.Vector#subtract(primitives.Vector)}.
     */
    @Test
    void subtract()
    {
      Vector v1 = new Vector(1.0, 1.0, 1.0);
      Vector v2 = new Vector(-1.0, -1.0, -1.0);

      Vector v3=v1.subtract(v2);
      assertTrue(v3.equals(new Vector(2.0,2.0,2.0)) );

      Vector v4=v2.subtract(v3);
      assertTrue(v4.equals(new Vector(-3.0,-3.0,-3.0)) );

    }

    /**
     * test for vector addition function {@link primitives.Vector#add(primitives.Vector)}.
     */
    @Test
    void add() {

        Vector v1 = new Vector(1.0, 1.0, 1.0);
        Vector v2 = new Vector(-2.0, -2.0, -2.0);

        v1 = v1.add(v2);
        assertTrue(v1.equals(new Vector(-1.0,-1.0,-1.0)) );

        v2 = v2.add(v1);
        assertTrue(v2.equals(new Vector(-3.0,-3.0,-3.0)) );
    }

    /**
     * test for vector scaling function (@link primitives.Vector# subtract(double)}.
     */
    @Test
    void scale() {
        Vector v1 = new Vector(1.0, 1.0, 1.0);

        v1.scale(1);
        assertTrue(v1.equals(v1));
       Vector v2=v1.scale(2);
        assertTrue(v2.equals(new Vector(2.0, 2.0, 2.0)));
        Vector v3=v2.scale(-2);
       assertTrue(v3.equals(new Vector(-4.0,-4.0,-4.0)));
    }

    /**
     * test for vector dot product function {@link primitives.Vector#dotProduct(primitives.Vector)}.
     */
    @Test
    void dotProduct() {
        Vector v1 = new Vector(3.5,-5,10);
        Vector v2 = new Vector(2.5,7,0.5);

        assertTrue(Double.compare(v1.dotProduct(v2), (8.75 + -35 + 5)) == 0);
        Vector v3 = new Vector(1, 2, 3);
        Vector v4 = new Vector(-2, -4, -6);
        assertEquals(v3.dotProduct(v4),-28);
    }

    /**
     * test for vector cross product function {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    void crossProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);

        // ============ Equivalence Partitions Tests ==============
        Vector v3 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v3);

        // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals( v1.length() * v3.length(), vr.length(), 0.00001);

        // Test cross-product result orthogonality to its operands
        assertTrue( Util.isZero(vr.dotProduct(v1)));
        assertTrue(Util.isZero(vr.dotProduct(v3)));

        // =============== Boundary Values Tests ==================
        // test zero vector from cross-product of co-lined vectors
        try {
            v1.crossProduct(v2);
            fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {}

    }

    /**
     * test for vector length squared function {@link primitives.Vector#lengthSquared()}.
     */
    @Test
    void lengthSquared() {
        Vector v = new Vector(3.5,-5,10);
        assertTrue(v.lengthSquared() ==  Math.sqrt(12.25 + 25 + 100)* Math.sqrt(12.25 + 25 + 100));
        Vector v1 = new Vector(1, 2, 3);
        assertEquals(v1.lengthSquared(),14);
    }

    /**
     * test for vector length function {@link primitives.Vector#length()}.
     */
    @Test
    void length() {
        Vector v = new Vector(3.5,-5,10);
        assertTrue(v.length() ==
                Math.sqrt(12.25 + 25 + 100));
        assertEquals(new Vector(0, 3, 4).length(),5);
    }

    /**
     * test for vector normalize function {@link primitives.Vector#normalize()}.
     */
    @Test
    void normalize() {
        // ============ Equivalence Partitions Tests ==============

        Vector v1= new Vector(new Point3D(1,5,4));
        v1.normalize();
        Coordinate c4=new Coordinate(1/(Math.sqrt(42)));
        Coordinate c5=new Coordinate(5/(Math.sqrt(42)));
        Coordinate c6=new Coordinate(4/(Math.sqrt(42)));
        Vector v2= new Vector(new Point3D(c4,c5,c6)); try {

            assertEquals(v1,v2);

        } catch (ArithmeticException e) {
            fail("the vectors should be equal");
        }
        Vector v3=new Vector(-1,-2,-3);
        v3.normalize();
        Point3D p=new Point3D(-1/Math.sqrt(14),-2/Math.sqrt(14),-3/Math.sqrt(14));

        assertTrue(v3.equals(new Vector(p)));
        Vector v = new Vector(1, 2, 3);
        Vector vCopy = new Vector(v);
        Vector vCopyNormalize = vCopy.normalize();
        assertTrue(vCopyNormalize.equals(vCopy));
    }

    /**
     * test for vector normalized function  {@link primitives.Vector#normalized()}.
     */
    @Test
    void normalized() {
        Vector v1=new Vector(2,3,4);
        Vector v= v1.normalized();
        Vector v2=new Vector(2/Math.sqrt(29),3/Math.sqrt(29),4/Math.sqrt(29));
        assertTrue(v.equals(v2));
        Vector v3 = new Vector(1, 2, 3);
        Vector u = v.normalized();
        assertTrue(!v3.equals(u));
    }
}