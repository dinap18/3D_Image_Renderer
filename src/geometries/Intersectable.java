package geometries;
import primitives.*;

import java.util.List;

/**
 *  interface that contains list of intersection points
 */
public interface Intersectable {
    List<Point3D> findIntersections(Ray ray);

}
