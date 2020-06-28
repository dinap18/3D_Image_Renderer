package unitTests.rendererTests;
import geometries.Plane;
import geometries.Polygon;
import geometries.Triangle;
import org.junit.Test;

import elements.*;
import geometries.Sphere;
import primitives.*;
import renderer.*;
import scene.Scene;
public class Bvhtest {
    @Test
    public void test()
    {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.set_distance(700);
        scene.set_background(new Color	(135, 193, 232));
        scene.set_ambientLight(new AmbientLight(new Color(135, 193, 232), 1));
        scene.addGeometries(
                new Plane(Color.BLACK,new Material(0.1,0.2,5,0,0.7),new Point3D(0,100,0),
                        new Vector(0,1,0)),
                new Sphere(new Color(212,175,55),//gold
                        new Material(0.3, 0.4, 5, 0, 0.4),30,
                        new Point3D(75, 0, -75)),
                new Sphere(new Color(170,169,173),//silver
                        new Material(0.3, 0.4, 5, 0, 0.4),30,
                        new Point3D(-75, 0, -75)),
                new Sphere(new Color(255,164,71),//orange
                        new Material(0.1, 0.3, 5, 0.8, 0),30,
                        new Point3D(0, 0, 60))
                ,
                new Sphere(new Color(255,164,71),//orange
                        new Material(0.25, 0.3, 5, 0, 0.4),20,
                        new Point3D(0, 0, 0)),
        new Sphere(new Color(170,169,173),//silver
                new Material(0.3, 0.4, 5, 0, 0.4),40,
                new Point3D(75, 75, 75))
                ,  new Sphere(new Color(212,175,55),//gold
                        new Material(0.3, 0.4, 5, 0, 0.4),30,
                        new Point3D(0, 75, -75))
      ,  new Sphere(new Color(255,164,71),//orange
                new Material(0.1, 0.3, 5, 0.8, 0),40,
                new Point3D(-75, 75, 75))
//                ,
//        new Sphere(new Color(170,169,173),//silver
//                new Material(0.3, 0.4, 5, 0, 0.4),100,
//                new Point3D(175, 60, -75))
                ,
                new Triangle(new Color(0,0,0), new Material(0.7, 0.5, 200,0.8,0), //
                        new Point3D(500, -500, 0), new Point3D(-500, 500, 0), new Point3D(100, 100, 100))
                ,
                new Triangle(new Color(0,0,0), new Material(0.7, 0.5, 200,0,0.8), //
                        new Point3D(500, -500, 0), new Point3D(500, 500, 0), new Point3D(-100, 100, 100))
        );
        scene.addLights(
                new DirectionalLight(new Color(210,210,210
                ),new Vector(0,1,0)),
                new SpotLight(new Color(130, 100, 130),new Point3D(0, 30, -50),
                        new Vector(0,-1,0),1, 4E-5, 2E-7),
                new PointLight(new Color(210,210,210),new Point3D(-160,165,100))
                ,new PointLight(new Color(210,210,210),new Point3D(160, 165, 100))
        );

        ImageWriter imageWriter = new ImageWriter("bvh", 200, 200, 500, 500);
        Render render = new Render(imageWriter, scene);
     //  render.set_numOfRays(10);

        render.set_rayDistance(100);
        render.set_threads(3).setPrint();
        render.renderImage();
        render.writeToImage();
    }
    @Test
    public void rayTracingTest2()
    {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.set_distance(500);
        scene.set_background(new Color	(0,0,0));
        scene.set_ambientLight(new AmbientLight(new Color(255, 191, 191), 1));

        scene.addGeometries(
//                new Plane(Color.BLACK,new Material(0.2,0.2,5,0,0.4),new Point3D(0,0,210),
//                        new Vector(0,0,-1)),
                new Sphere(new Color(212,175,55),//gold
                        new Material(0.3, 0.4, 5, 0, 0.4),30,
                        new Point3D(75, 0, -75)),
                new Sphere(new Color(170,169,173),//silver
                        new Material(0.3, 0.4, 5, 0, 0.4),30,
                        new Point3D(-75, 0, -75)),
                new Sphere(new Color(255,164,71),//orange
                        new Material(0.1, 0.3, 5, 0.8, 0),30,
                        new Point3D(0, 0, 60))
                ,
                new Sphere(new Color(255,164,71),//orange
                        new Material(0.25, 0.3, 5, 0, 0.4),20,
                        new Point3D(0, 0, 0)),
                new Sphere(new Color(170,169,173),//silver
                        new Material(0.3, 0.4, 5, 0, 0.4),40,
                        new Point3D(75, 75, 75))
                ,  new Sphere(new Color(212,175,55),//gold
                        new Material(0.3, 0.4, 5, 0, 0.4),30,
                        new Point3D(0, 75, -75))
                ,  new Sphere(new Color(255,164,71),//orange
                        new Material(0.1, 0.3, 5, 0.8, 0),40,
                        new Point3D(-75, 75, 75))
                ,

                new Sphere(new Color(212,175,55),//gold
                        new Material(0.2, 0.4, 5, 0.8, 0),20,
                        new Point3D(75, 120, -75)),
                new Sphere(new Color(53,187,202)	,//blue
                        new Material(0.25, 0.3, 5, 0.22, 0),15,
                        new Point3D(160, 165, 100)),
                new Sphere(new Color(255,99,71),//red
                        new Material(0.25, 0.3, 5, 0,0.4),25,
                        new Point3D(0, 130, 90)),
                new Sphere(new Color(255,164,71),//orange
                        new Material(0.25, 0.3, 5, 0, 0.4),25,
                        new Point3D(0, 130, -60)),
                new Sphere(new Color(0,128,85), // Biggest Blue
                        new Material(0.3, 0.2, 5, 0,0),50,
                        new Point3D(-53, -50, 200)),
                new Sphere(new Color	(0,128,128),//biggest teal
                        new Material(0.3, 0.2, 5, 0,0),50,
                        new Point3D(53, -50, 200)),
                new Sphere(new Color(170,169,173),//silver
                        new Material(0.3, 0.4, 5, 0, 0.4),20,
                        new Point3D(-75, 120, -75)),
                new Sphere(new Color	(53,202,93),//green
                        new Material(0.3, 0.4, 5, 0.22, 0),15,
                        new Point3D(-160, 165, 100))
,
                new Sphere(new Color(255,164,71),//orange
                        new Material(0.25, 0.3, 5, 0, 0.4),25,
                        new Point3D(50, 200, -60)),
                new Sphere(new Color(0,128,85), // Biggest Blue
                        new Material(0.3, 0.2, 5, 0,0),50,
                        new Point3D(-40, 80, 200)),
                new Sphere(new Color	(0,128,128),//biggest teal
                        new Material(0.3, 0.2, 5, 0,0),50,
                        new Point3D(32, 67, 200)),
                new Sphere(new Color(300,169,173),//silver
                        new Material(0.3, 0.4, 5, 0, 0.4),20,
                        new Point3D(-75, 145, -75)),
                new Sphere(new Color	(53,202,93),//green
                        new Material(0.3, 0.4, 5, 0.22, 0),15,
                        new Point3D(-99, 187, 100))
                ,
                new Sphere(new Color(255,164,71),//orange
                        new Material(0.25, 0.3, 5, 0, 0.4),25,
                        new Point3D(50, 200, 60)),
                new Sphere(new Color(0,128,85), // Biggest Blue
                        new Material(0.3, 0.2, 5, 0,0),50,
                        new Point3D(-40, 80, -20)),
                new Sphere(new Color	(0,128,128),//biggest teal
                        new Material(0.3, 0.2, 5, 0,0),50,
                        new Point3D(32, 67, 200)),
                new Sphere(new Color(300,169,173),//silver
                        new Material(0.3, 0.4, 5, 0, 0.4),20,
                        new Point3D(-75, 145, -75)),
                new Sphere(new Color	(53,202,93),//green
                        new Material(0.3, 0.4, 5, 0.22, 0),15,
                        new Point3D(-99, 187, 100))
//                ,
//                new Sphere(new Color(255,164,71),//orange
//                        new Material(0.25, 0.3, 5, 0, 0.4),25,
//                        new Point3D(76, 66, -65)),
//                new Sphere(new Color(0,128,85), // Biggest Blue
//                        new Material(0.3, 0.2, 5, 0,0),50,
//                        new Point3D(-34, 71, 35)),
//                new Sphere(new Color	(0,128,128),//biggest teal
//                        new Material(0.3, 0.2, 5, 0,0),50,
//                        new Point3D(98, 56, 146)),
//                new Sphere(new Color(300,169,173),//silver
//                        new Material(0.3, 0.4, 5, 0, 0.4),20,
//                        new Point3D(-67, 34, -12)),
//                new Sphere(new Color	(53,202,93),//green
//                        new Material(0.3, 0.4, 5, 0.22, 0),15,
//                        new Point3D(-16, 56, 5))
        );


        scene.addLights(
                new DirectionalLight(new Color(210,210,210
                ),new Vector(0,1,0)),
                new SpotLight(new Color(130, 100, 130),new Point3D(0, 30, -50),
                        new Vector(0,-1,0),1, 4E-5, 2E-7),
                new PointLight(new Color(210,210,210),new Point3D(-160,165,100))
                ,new PointLight(new Color(210,210,210),new Point3D(160, 165, 100)),new SpotLight(new Color(400, 240, 0), //
                        new Point3D(-100, 100, -200), new Vector(1, -1, 3), 1, 1E-5, 1.5E-7));


        ImageWriter imageWriter = new ImageWriter(" 5 bvh", 200, 200, 500, 500);
        Render render = new Render(imageWriter, scene);
    //    render.set_numOfRays(50);
     render.setBvh(true);
        render.set_rayDistance(1);
        render.set_threads(3).setPrint();
        render.renderImage();
        render.writeToImage();
    }

}
