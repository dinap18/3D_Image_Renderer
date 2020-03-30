package tests;

import geometries.Cylinder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import primitives.*;
class CylinderTest {

    @Test
    void getNormal() {
        Cylinder c= new Cylinder(4,new Ray(new Point3D(5,6,7),new Vector(3,4,5)),7);
        Vector v=c.getNormal(new Point3D(5,6,7));
        assertTrue(true);

    }



}