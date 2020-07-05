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
//    @Test
//    public void test()
//    {
//        Scene scene = new Scene("Test scene");
//        scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
//        scene.set_distance(30);
//        scene.set_background(new Color	(135, 193, 232));
//        scene.set_ambientLight(new AmbientLight(new Color(135, 193, 232), 1));
//        scene.addGeometries(
//                new Plane(Color.BLACK,new Material(0.1,0.2,5,0,0.7),new Point3D(0,100,0),
//                        new Vector(0,1,0)),
//                new Sphere(new Color(212,175,55),//gold
//                        new Material(0.3, 0.4, 5, 0, 0.4),30,
//                        new Point3D(75, 0, -75)),
//                new Sphere(new Color(170,169,173),//silver
//                        new Material(0.3, 0.4, 5, 0, 0.4),30,
//                        new Point3D(-75, 0, -75)),
//                new Sphere(new Color(255,164,71),//orange
//                        new Material(0.1, 0.3, 5, 0.8, 0),30,
//                        new Point3D(0, 0, 60))
//                ,
//                new Sphere(new Color(255,164,71),//orange
//                        new Material(0.25, 0.3, 5, 0, 0.4),20,
//                        new Point3D(0, 0, 0)),
//        new Sphere(new Color(170,169,173),//silver
//                new Material(0.3, 0.4, 5, 0, 0.4),40,
//                new Point3D(75, 75, 75))
//                ,  new Sphere(new Color(212,175,55),//gold
//                        new Material(0.3, 0.4, 5, 0, 0.4),30,
//                        new Point3D(0, 75, -75))
//      ,  new Sphere(new Color(255,164,71),//orange
//                new Material(0.1, 0.3, 5, 0.8, 0),40,
//                new Point3D(-75, 75, 75))
////                ,
////        new Sphere(new Color(170,169,173),//silver
////                new Material(0.3, 0.4, 5, 0, 0.4),100,
////                new Point3D(175, 60, -75))
//                ,
//                new Triangle(new Color(0,0,0), new Material(0.7, 0.5, 200,0.8,0), //
//                        new Point3D(500, -500, 0), new Point3D(-500, 500, 0), new Point3D(100, 100, 100))
//                ,
//                new Triangle(new Color(0,0,0), new Material(0.7, 0.5, 200,0,0.8), //
//                        new Point3D(500, -500, 0), new Point3D(500, 500, 0), new Point3D(-100, 100, 100))
//        );
//        scene.addLights(
//                new DirectionalLight(new Color(210,210,210
//                ),new Vector(0,1,0)),
//                new SpotLight(new Color(130, 100, 130),new Point3D(0, 30, -50),
//                        new Vector(0,-1,0),1, 4E-5, 2E-7),
//                new PointLight(new Color(210,210,210),new Point3D(-160,165,100))
//                ,new PointLight(new Color(210,210,210),new Point3D(160, 165, 100))
//        );
//
//        ImageWriter imageWriter = new ImageWriter("bvh", 200, 200, 500, 500);
//        Render render = new Render(imageWriter, scene);
//     //  render.set_numOfRays(10);
//
//        render.set_rayDistance(100);
//        render.set_threads(3).setPrint();
//        render.renderImage();
//        render.writeToImage();
//    }
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
                        new Point3D(145, 0, -75))  ,
                new Sphere(new Color(212,175,55),//gold
                        new Material(0.3, 0.4, 5, 0, 0.4),30,
                        new Point3D(0, 75, -75)),
                new Sphere(new Color(212,175,55),//gold
                        new Material(0.2, 0.4, 5, 0.8, 0),20,
                        new Point3D(75, 120, -75)),

                new Sphere(new Color(300,169,173),//silver
                        new Material(0.3, 0.4, 5, 0, 0.4),20,
                        new Point3D(143, -140, -75)),
                new Sphere(new Color(170,169,173),//silver
                        new Material(0.3, 0.4, 5, 0, 0.4),30,
                        new Point3D(-100, 0, -75)),
                new Sphere(new Color(170,169,173),//silver
                        new Material(0.3, 0.4, 5, 0, 0.4),40,
                        new Point3D(70, -175, -75)),
                new Sphere(new Color(170,169,173),//silver
                        new Material(0.3, 0.4, 5, 0, 0.4),20,
                        new Point3D(-100, 120, -75)),
                new Sphere(new Color(300,169,173),//silver
                        new Material(0.3, 0.4, 5, 0, 0.4),20,
                        new Point3D(-150, 145, -75)),


                new Sphere(new Color(255,164,71),//orange
                        new Material(0.1, 0.3, 5, 0.8, 0),30,
                        new Point3D(0, 0, 0))
                ,
                new Sphere(new Color(255,164,71),//orange
                        new Material(0.25, 0.3, 5, 0, 0.4),20,
                        new Point3D(0, 0, 0)),
                new Sphere(new Color(255,164,71),//orange
                        new Material(0.1, 0.3, 5, 0.8, 0),40,
                        new Point3D(-146, 75, 75))
                ,
                new Sphere(new Color(255,164,71),//orange
                        new Material(0.25, 0.3, 5, 0, 0.4),25,
                        new Point3D(80, 180, 60)),
                new Sphere(new Color(255,164,71),//orange
                        new Material(0.25, 0.3, 5, 0, 0.4),25,
                        new Point3D(178, 100, -60)),
                new Sphere(new Color(255,164,71),//orange
                        new Material(0.25, 0.3, 5, 0, 0.4),25,
                        new Point3D(50, 180, -60)),




//                new Sphere(new Color(200,187,202)	,//blue
//                        new Material(0.25, 0.3, 5, 0.22, 0),15,
//                        new Point3D(200, 180, 100)),




                new Sphere(new Color(255,99,71),//red
                        new Material(0.25, 0.3, 5, 0,0.4),25,
                        new Point3D(99, 130, 90)),



                new Sphere(new Color	(53,202,93),//green
                        new Material(0.3, 0.4, 5, 0.22, 0),15,
                        new Point3D(-200, 200, 100))
                ,
                new Sphere(new Color	(53,202,93),//green
                        new Material(0.3, 0.4, 5, 0.22, 0),15,
                        new Point3D(75, 165, 100))
                ,

                new Sphere(new Color	(53,202,93),//green
                        new Material(0.3, 0.4, 5, 0.22, 0),15,
                        new Point3D(-75, -165, 100))




                ,
                new Sphere(new Color(0,128,85), // Biggest Blue
                        new Material(0.3, 0.2, 5, 0,0),50,
                        new Point3D(-80, 50, 200)),
                new Sphere(new Color(0,128,85), // Biggest Blue
                        new Material(0.3, 0.2, 5, 0,0),50,
                        new Point3D(-140, -80, -20)),
                new Sphere(new Color(0,128,85), // Biggest Blue
                        new Material(0.3, 0.2, 5, 0,0),50,
                        new Point3D(-33, -30, 200)),



                new Sphere(new Color	(0,128,128),//biggest teal
                        new Material(0.3, 0.2, 5, 0,0),50,
                        new Point3D(75, -50, 200)),
                new Sphere(new Color	(0,128,128),//biggest teal
                        new Material(0.3, 0.2, 5, 0,0),50,
                        new Point3D(-54, 67, 200)),
                new Sphere(new Color	(0,128,128),//biggest teal
                        new Material(0.3, 0.2, 5, 0,0),50,
                        new Point3D(100, -36, 200))
                , new Triangle(new Color(20, 20, 20), new Material(0, 0, 0, 0, 1), new Point3D(1500, 1500, 1500),
                        new Point3D(-1500, -1500, 1500), new Point3D(670, -670, -3000)),
                new Triangle(new Color(20, 20, 20), new Material(0, 0, 0, 0, 0.5), new Point3D(1500, 1500, 1500),
                        new Point3D(-1500, -1500, 1500), new Point3D(-1500, 1500, 2000))
                ,
                new Sphere(new Color(255,164,71),//orange
                        new Material(0.25, 0.3, 5, 0, 0.4),25,
                        new Point3D(76, 66, -65)),
                new Sphere(new Color(0,128,85), // Biggest Blue
                        new Material(0.3, 0.2, 5, 0,0),50,
                        new Point3D(-145, 71, 35)),
                new Sphere(new Color	(0,128,128),//biggest teal
                        new Material(0.3, 0.2, 5, 0,0),50,
                        new Point3D(98, 56, 146)),
                new Sphere(new Color(300,169,173),//silver
                        new Material(0.3, 0.4, 5, 0, 0.4),20,
                        new Point3D(-67, 34, -12)),
                new Sphere(new Color	(53,202,93),//green
                        new Material(0.3, 0.4, 5, 0.22, 0),15,
                        new Point3D(-16, 56, 5)),
                new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
                        new Point3D(-150, 150, 200), new Point3D(150, 150, 35), new Point3D(75, -75, 200)), //
                new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
                        new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 115)), //
                new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), // )
                        30, new Point3D(60, -50, 50)),
                new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
                        new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150)),

                new Triangle(new Color(255, 191, 191), new Material(0.5, 0.5, 60), //
                        new Point3D(120, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150)),
//                new Sphere(new Color(255,255,102),
//                        new Material(0.25,0.25,20,0.9,0),16,new Point3D(-80,-79,-70)),
                new Sphere(new Color(204, 0, 201),
                        new Material(0.25, 0.25, 20,0.9,0), 50, new Point3D(-175, -160, -100)),
                new Sphere(new Color(102, 0, 204),
                        new Material(0.25, 0.25, 20,0,0.6), 40, new Point3D(-95, 90, 100))
        );


        scene.addLights(
                new DirectionalLight(new Color(210,210,210
                ),new Vector(0,1,0)),
                new SpotLight(new Color(130, 100, 130),new Point3D(0, 30, -50),
                        new Vector(0,-1,0),1, 4E-5, 2E-7),
                new PointLight(new Color(210,210,210),new Point3D(-160,165,100))
                ,new PointLight(new Color(210,210,210),new Point3D(160, 165, 100)),new SpotLight(new Color(400, 240, 0), //
                        new Point3D(-100, 100, -200), new Vector(1, -1, 3), 1, 1E-5, 1.5E-7)
        );


        ImageWriter imageWriter = new ImageWriter(" bvh", 200, 200, 500, 500);
        Render render = new Render(imageWriter, scene);
        render.set_numOfRays(10);
        render.setBvh(true);
        render.set_rayDistance(1);
        render.set_threads(3);
        render.renderImage();
        render.writeToImage();
    }

}