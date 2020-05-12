package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * geometries class that implements the intersectable interface
 */
public class Geometries implements Intersectable {
    private List<Intersectable> _geometries;
    public Geometries(Intersectable... _geometries)
    {
        add( _geometries);
    }

    public Geometries() {
        _geometries = new ArrayList<Intersectable>();
    }

    /**
     * adds a new shape to the geometry list
     * @param geometries
     */
    public void add(Intersectable... geometries)
    {
        for (Intersectable geo : geometries )
        {
            _geometries.add(geo);
        }
    }

    /**
     * finds intersections between a list of geometries and a ray
     * @param ray
     * @return list of intersection points from  a list of geometries and a ray
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        List<GeoPoint> intersections = null;

        for (Intersectable geo : _geometries) {
            List<GeoPoint> tempIntersections = geo.findIntersections(ray);
            if (tempIntersections != null) {
                if (intersections == null)
                    intersections = new LinkedList<GeoPoint>();
                intersections.addAll(tempIntersections);
            }
        }
        return intersections;

    }
    /**
     * adds all intersectable geometries to list
     * @param geometries list of intersectable geometries
     */
    public void addAll(Intersectable... geometries) {
        for (Intersectable geo : geometries) {
            _geometries.add(geo);
        }
    }
    /**
     * removes geometries from list of intersectable geometries
     * @param intersectables instersectable geometries to remove from list
     */
    public void remove(Intersectable... intersectables) {
        for (Intersectable geo : _geometries) {
            _geometries.remove(geo);
        }
    }
}
