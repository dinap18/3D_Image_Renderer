package unitTests.elementTests;

import elements.Camera;
import org.junit.jupiter.api.Test;
import geometries.*;
import primitives.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * camera integration tests with a sphere, plane, and triangle.
 */
public class CameraIntegrationTests {
    /**
     * integration tests for constructing a ray through a pixel with a sphere
     * {@link elements.Camera#constructRayThroughPixel(int, int, int, int, double, double, double)}.
     */
    @Test

    void constructRayThroughPixelWithSphere()
    {
        //TC01: 2 intersection points
        Camera cam1 = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
        Camera cam2 = new Camera(new Point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));
        Sphere sph =  new Sphere(1, new Point3D(0, 0, 3));
        List<Intersectable.GeoPoint> results;
        int count = 0;
        int Nx =3;
        int Ny =3;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                results = sph.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals(2,count,"too bad");
        //TC02: 18 intersection points
         Sphere sph2 =  new Sphere(2.5, new Point3D(0, 0, 2.5));
          count = 0;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                results = sph2.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals(18,count,"wrong amount of intersections");
        //TC03: 10 intersection points
        Sphere sph3= new Sphere(2,new Point3D(0,0,2));
        count=0;
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 3; ++j)
            {
                results = sph3.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }
        assertEquals(10,count,"wrong amount of intersections");
        //TC04: 9 intersection points
        Sphere sph4= new Sphere(4,new Point3D ( 0,0,1));
        count =0;
        for ( int i=0;i<3 ; ++i)
            for(int j = 0;j<3 ;++j)  {
                results = sph4.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        assertEquals(9,count,"wrong amount of intersections");

        //TC05: 0 intersection points
        Sphere sph5= new Sphere (0.5,new Point3D(0,0,-1));
        count = 0;
        for ( int i=0;i<3 ; ++i)
            for(int j = 0;j<3 ;++j)  {
                results = sph5.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        assertEquals(0,count,"wrong amount of intersections");


    }

    /**
     * integration tests for constructing a ray through a pixel with a plane
     * {@link elements.Camera#constructRayThroughPixel(int, int, int, int, double, double, double)}.
     */
    @Test
    public void constructRayThroughPixelWithPlane()
    {
        Camera cam = new Camera (Point3D.ZERO, new Vector (0,0,1), new Vector (0,-1,0));
        List<Intersectable.GeoPoint> results;
        int count;
        //Tc01: 9 intersection points- plane against camera
        Plane plane1=new Plane ( new Point3D (0,0,5), new Vector (0,0,1));
         count = 0;
        for ( int i=0;i<3 ; ++i)
            for(int j = 0;j<3 ;++j)  {
                results = plane1.findIntersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        assertEquals(9,count,"wrong amount of intersections");
        //TC02: 9 intersection points- plane with small angle
        Plane plane2=new Plane ( new Point3D (0,0,5),new Vector (0,-1,2));
        count = 0;
        for ( int i=0;i<3 ; ++i)
            for(int j = 0;j<3 ;++j)  {
                results = plane2.findIntersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        assertEquals(9,count,"wrong amount of intersections");
        //TC03: 6 intersection points- plane parallel to lower rays
        Plane plane3 = new Plane ( new Point3D(0,0,5), new Vector (0,-1,1));
        count = 0;
        for ( int i=0;i<3 ; ++i)
            for(int j = 0;j<3 ;++j)  {
                results = plane3.findIntersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        assertEquals(6,count,"wrong amount of intersections");
            //TC04: 0 intersection points - beyond plane
        Plane plane4= new Plane ( new Point3D (0,0,-5), new Vector (0,0,1));
        count = 0;
        for ( int i=0;i<3 ; ++i)
            for(int j = 0;j<3 ;++j)  {
                results = plane4.findIntersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        assertEquals(0,count,"wrong amount of intersections");
    }
    /**
     * integration tests for constructing a ray through a pixel with a triangle
     * {@link elements.Camera#constructRayThroughPixel(int, int, int, int, double, double, double)}.
     */
    @Test
    public void constructRayThroughPixelWithTriangle()
    {
        Camera cam= new Camera(Point3D.ZERO,new Vector(0,0,1),new Vector(0,-1,0));
        List<Intersectable.GeoPoint>results;
        int count=0;
        //TC01: 1 intersection point- small triangle
        Triangle tr1= new Triangle ( new Point3D(1,1,2),new Point3D(-1,1,2), new Point3D (0,-1,2));
        for ( int i=0;i<3 ; ++i)
            for(int j = 0;j<3 ;++j)  {
                results = tr1.findIntersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        assertEquals(1,count,"wrong amount of intersections");
        //TC02: 2 intersection points - medium triangle
        Triangle tr2= new Triangle ( new Point3D(1,1,2),new Point3D(-1,1,2), new Point3D (0,-20,2));
        count = 0;
        for ( int i=0;i<3 ; ++i)
            for(int j = 0;j<3 ;++j)  {
                results = tr2.findIntersections(cam.constructRayThroughPixel(3, 3, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        assertEquals(2,count,"wrong amount of intersections");


    }
}
