package unitTests.geometryTests;
import geometries.Cylinder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import primitives.*;

/**
 * cylinder tests
 */
class CylinderTest {
    /**
     * test for cylinder get normal function @link geometries.Cylinder#getNormal(primitives.Point3D)}.
     */
    @Test
    void getNormal() {
        Point3D p0=new Point3D(6,4,3);
        Vector direction=new Vector(3,4,5);
        Ray r=new Ray(p0,direction);
        Cylinder c= new Cylinder(4,r,7);
        Vector n=c.getNormal(new Point3D(6,5,3));
        Vector check=new Vector(-0.2910427500435999, 0.8246211251235319, -0.48507125007266594);
       assertTrue(n.equals(check));

    }



}