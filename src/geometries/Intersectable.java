package geometries;
import primitives.*;

import java.util.List;

/**
 * intersectable interface- contains list of intersection points
 */
public interface Intersectable {
    List<Point3D> findIntersections(Ray ray);

}
