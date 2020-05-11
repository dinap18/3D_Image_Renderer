package renderer;

import elements.AmbientLight;
import elements.Camera;
import geometries.Intersectable;
import primitives.*;

import scene.Scene;

import java.awt.Color;
import java.util.List;

/**
 * class Render
 */
public class Render {
    private final ImageWriter _imageWriter;
    private final Scene _scene;

   /* public Render(Scene _scene,ImageWriter _imageWriter) {
        this._scene = _scene;
        this._imageWriter=_imageWriter;
    }*/

    /**
     * constructor for class render
     * @param imageWriter image writer
     * @param scene scene to write image for
     */
    public Render(ImageWriter imageWriter, Scene scene) {
        this._imageWriter = imageWriter;
        this._scene=   scene;
    }

    /**
     * get scene function
     * @return scene
     */
    public Scene get_scene() {
        return _scene;
    }

    /**
     * Filling the buffer according to the geometries that are in the scene.
     * This function does not creating the picture file, but create the buffer pf pixels
     */
    public void renderImage() {
        Camera camera = _scene.getCamera();
        Intersectable geometries = _scene.getGeometries();
        java.awt.Color background = _scene.getBackground().getColor();
        AmbientLight ambientLight = _scene.getAmbientLight();
        double distance = _scene.getDistance();

        int Nx = _imageWriter.getNx();
        int Ny = _imageWriter.getNy();
        double width = _imageWriter.getWidth();
        double height = _imageWriter.getHeight();

        for (int row = 0; row < Ny; row++) {
            for (int collumn = 0; collumn < Nx; collumn++) {
                Ray ray = camera.constructRayThroughPixel(Nx, Ny, collumn, row, distance, width, height);
                List<Point3D> intersectionPoints = geometries.findIntersections(ray);
                if (intersectionPoints == null) {
                    _imageWriter.writePixel(collumn, row, background);
                } else {
                    Point3D closestPoint = getClosestPoint(intersectionPoints);
                    _imageWriter.writePixel(collumn, row, calcColor(closestPoint).getColor());
                }
            }
        }
    }

    /**
     * Finding the closest point to the P0 of the camera.
     * @param  intersectionPoints list of points, the function should find from
     * this list the closet point to P0 of the camera in the scene.
     * @return  the closest point to the camera
     */

    private Point3D getClosestPoint(List<Point3D> intersectionPoints) {
        Point3D p0 = _scene.getCamera().get_p0();
        Point3D pt = null;
        double minDist = Double.MAX_VALUE;
        double currentDistance = 0;

        for (Point3D point : intersectionPoints) {
            currentDistance = p0.distance(point);
            if (currentDistance < minDist) {
                minDist = currentDistance;
                pt = point;
            }
        }
        return pt;
    }

    /**
     * Printing the grid with a fixed interval between lines
     * @param interval The interval between the lines.
     */
    public void printGrid(int interval,Color colorsep) {
        double rows = this._imageWriter.getNx();
        double columns = _imageWriter.getNy();
        //Writing the lines.
        for (int col = 0; col < columns; col++) {
            for (int row = 0; row < rows; row++) {
                if (col % interval == 0 || row % interval == 0) {
                    _imageWriter.writePixel(row, col, colorsep);
                }
            }
        }
    }

    public void writeToImage() {
        _imageWriter.writeToImage();
    }

    /**
     * Calculate the color intensity in a point
     * @param point the point for which the color is required
     * @return the color intensity
     */
    private primitives.Color calcColor(Point3D point) {
        return _scene.getAmbientLight().getIntensity();
    }

}