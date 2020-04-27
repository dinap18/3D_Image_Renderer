package unitTests.geometryTests;

import geometries.Tube;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
import primitives.*;
/**
 * tube tests
 *
 */
class TubeTest {
    /**
     * test for tube get normal function {@link geometries.Tube#getNormal(primitives.Point3D)}.
     * }
     */
    @Test
    void getNormal() {
        Tube t= new Tube(4,new Ray(new Point3D(3,5,8),new Vector(4,7,6)));
        Vector n=t.getNormal(new Point3D(4,2,1));
        Vector check=new Vector(0.6736255682471604, 0.21987778192043828, -0.7056077910719512);
        assertTrue(n.equals(check));

    }
}