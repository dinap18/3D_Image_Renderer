package geometries;

import primitives.Ray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * geometries class that implements the intersectable interface
 */
public class Geometries implements Intersectable {
    /**
     * field for class Geometries - list of intersectable geometries
     */
    private List<Intersectable> _geometries;

    /**
     * constructor for class Geometries
     * @param _geometries - list of intersectable geometries to add to the list of intersectables
     */
    public Geometries(Intersectable... _geometries)
    {
        add( _geometries);
    }

    /**
     * default constructor for class geometries
     */
    public Geometries() {
        _geometries = new ArrayList<Intersectable>();//creates a new list for intersectable geometries
    }

    /**
     * adds new shapes to the geometry list
     * @param geometries - list of intersectable geometries
     */
    public void add(Intersectable... geometries)
    {
        for (Intersectable geo : geometries )// adds each of the geometries the function recieved to to the list of intersectable geometries
        {
            _geometries.add(geo);
        }
    }

    /**
     * finds intersections between a list of geometries and a ray
     * @param ray - to find intersections with
     * @return list of intersection points from  a list of geometries and a ray
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        List<GeoPoint> intersections = new LinkedList<GeoPoint>();

        for (Intersectable geo : _geometries) //finds the intersection points for each geometry and adds them to the list of geopoints to be returned
        {
            List<GeoPoint> tempIntersections = geo.findIntersections(ray);
            if (tempIntersections != null) //if intersection points were found we need to add them to the list
            {
                intersections.addAll(tempIntersections);
            }
        }
        if (intersections.size()==0)//if none of the shapes have intersection points with the ray null is returned
            return null;
        return intersections;

    }
    /**
     * adds all intersectable geometries to list
     * @param geometries list of intersectable geometries
     */
    public void addAll(Intersectable... geometries)
    {
        for (Intersectable geo : geometries) //adds each shape the function receives to the list of intersectable geometries
        {
            _geometries.add(geo);
        }
    }
    /**
     * removes geometries from list of intersectable geometries
     * @param intersectables instersectable geometries to remove from list
     */
    public void remove(Intersectable... intersectables) {
        for (Intersectable geo : _geometries)//removes each shaped received by the function from the list of intersectable geometries
        {
            _geometries.remove(geo);
        }
    }
}
