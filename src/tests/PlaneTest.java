package tests;
import primitives.*;
import geometries.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    @Test
    void getNormal() {
        Plane p=new Plane
                (new Point3D(new Coordinate(3),new Coordinate(2),new Coordinate(0))
                        ,new Vector(new Point3D(new Coordinate(0),new Coordinate(0),new Coordinate(1))));
        assertEquals(p.getNormal(new Point3D(new Coordinate(3),new Coordinate(2),new Coordinate(0))),new Vector(new Point3D(new Coordinate(0),new Coordinate(0),new Coordinate(1))));
    }

    @Test
    void get_p() {
    }

    @Test
    void get_normal() {
    }

    @Test
    void testToString() {
    }
}