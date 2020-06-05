package unitTests.rendererTests;
import geometries.Polygon;
import org.junit.Test;

import elements.*;
import geometries.Sphere;
import primitives.*;
import renderer.*;
import scene.Scene;
public class RayTracingTest {
    @Test
    public void rayTracingTest()
    {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.set_distance(600);
        scene.set_background(new Color	(135,206,235));
        scene.set_ambientLight(new AmbientLight(new Color(255, 191, 191), 1));

        scene.addGeometries(
                new Sphere(new Color(212,175,55),//gold
                     new Material(0.3, 0.4, 30, 0, 0.75),20,
                        new Point3D(75, 120, -75)),
                new Sphere(new Color(53,187,202)	,//blue
                        new Material(0.25, 0.3, 80, 0.5, 0.6),15,
                        new Point3D(160, 165, 100)),
                new Sphere(new Color(255,99,71),//red
                        new Material(0.25, 0.3, 100, 0.5, 0.5),25,
                        new Point3D(0, 130, 90)),
                new Sphere(new Color(255,164,71),//orange
                        new Material(0.25, 0.3, 100, 0.5, 0.5),25,
                        new Point3D(0, 130, -60)),
                new Sphere(new Color(0,128,85), // Biggest Blue
                          new Material(0.4, 0.35, 5, 0.2,0.8),50,
                          new Point3D(-75, -50, 80)),
                new Sphere(new Color	(0,128,128),//biggest teal
                        new Material(0.4, 0.35, 5, 0.2,0.8),50,
                        new Point3D(75, -50, 80)),
                new Sphere(new Color(170,169,173),//silver
                        new Material(0.3, 0.4, 30, 0, 0.75),20,
                        new Point3D(-75, 120, -75)),
                new Sphere(new Color	(53,202,93),//green
                        new Material(0.3, 0.4, 80, 0.5, 0.6),15,
                        new Point3D(-160, 165, 100))

               , new Polygon(new Color(0,0,0),new Material(0.3,0.5,60,0.8,0.2),
                        new Point3D(200,-200,200),new Point3D(200,200,200),
                        new Point3D(-200,200,200),new Point3D(-200,-200,200))
);

        scene.addLights(
                new DirectionalLight(new Color(300,255,255
        ),new Vector(0,1,0))
       ,new SpotLight(new Color(130, 100, 130),new Point3D(0, 30, -50),
              new Vector(0,-1,0),1, 4E-5, 2E-7)
                ,new PointLight(new Color(230,230,250),new Point3D(-160,165,100))
                ,new PointLight(new Color(230,230,250),new Point3D(160, 165, 100))
        );

        ImageWriter imageWriter = new ImageWriter("ray tracing scene", 200, 200, 500, 500);
        Render render = new Render(imageWriter, scene);
        render.set_numOfRays(1);
        render.setRadius(15);
        render.set_rayDistance(2);
        render.renderImage();
        render.writeToImage();
    }
    @Test
    public void rayTracingTest2()
    {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.set_distance(600);
        scene.set_background(new Color	(0,0,0));
        scene.set_ambientLight(new AmbientLight(new Color(255, 191, 191), 1));

        scene.addGeometries(

                new Sphere(new Color(212,175,55),//gold
                        new Material(0.3, 0.4, 5, 0, 0.5),20,
                        new Point3D(75, 120, -75)),
                new Sphere(new Color(53,187,202)	,//blue
                        new Material(0.25, 0.3, 5, 0, 0),15,
                        new Point3D(160, 165, 100)),
                new Sphere(new Color(255,99,71),//red
                        new Material(0.25, 0.3, 5, 0,0.5 ),25,
                        new Point3D(0, 130, 90)),
                new Sphere(new Color(255,164,71),//orange
                        new Material(0.25, 0.3, 5, 0, 0.5),25,
                        new Point3D(0, 130, -60)),
                new Sphere(new Color(0,128,85), // Biggest Blue
                        new Material(0.3, 0.2, 5, 0,0.1),50,
                        new Point3D(-75, -50, 80)),
                new Sphere(new Color	(0,128,128),//biggest teal
                        new Material(0.3, 0.2, 5, 0,0.1),50,
                        new Point3D(75, -50, 80)),
                new Sphere(new Color(170,169,173),//silver
                        new Material(0.3, 0.4, 5, 0, 0.5),20,
                        new Point3D(-75, 120, -75)),
                new Sphere(new Color	(53,202,93),//green
                        new Material(0.3, 0.4, 5, 0, 0),15,
                        new Point3D(-160, 165, 100))


        );

        scene.addLights(
                new DirectionalLight(new Color(240,240,240
                ),new Vector(0,1,0)),
//                new SpotLight(new Color(130, 100, 130),new Point3D(0, 30, -50),
//                        new Vector(0,-1,0),1, 4E-5, 2E-7)
                new PointLight(new Color(230,230,250),new Point3D(-160,165,100))
                ,new PointLight(new Color(230,230,250),new Point3D(160, 165, 100))
        );

        ImageWriter imageWriter = new ImageWriter("ray tracing 2", 200, 200, 500, 500);
        Render render = new Render(imageWriter, scene);
        render.set_numOfRays(50);

        render.setRadius(40);
       render.set_rayDistance(5);
       render.set_threads(4);
       render.setPrint();
        render.renderImage();
        render.writeToImage();
    }
}
