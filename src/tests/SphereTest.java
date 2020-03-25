package tests;
import primitives.*;
import geometries.*;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    @Test
    void getNormal() {
        Point3D p=new Point3D(new Coordinate(1),new Coordinate(0),new Coordinate(0));
        Sphere s=new Sphere(3,p);
        Vector v1=new Vector(s.getNormal(p));
        assertEquals(v1.get_head().get_x(), 0.0);
        assertEquals(v1.get_head().get_y(), 0.0);
        assertEquals(v1.get_head().get_z(), 0.0);

    }

    @Test
    void get_center() {
    }

    @Test
    void testToString() {
    }
}