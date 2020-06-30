package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/**
 * geometries class that extends the intersectable abstract class
 */
public class Geometries extends Intersectable {
    /**
     * field for class Geometries - list of intersectable geometries
     */
    private List<Intersectable> _geometries;
    private List<Geometries>_geoList=new LinkedList<>();

    public List<Intersectable> get_geometries() {
        return _geometries;
    }

    /**
     *
     * constructor for class Geometries
     * @param _geometries - list of intersectable geometries to add to the list of intersectables
     */
    public Geometries(Geometries... _geometries)
    {
        this();
        add( _geometries);
    }

    /**
     * default constructor for class geometries
     */
    public Geometries() {
        //for BVH
        if (this.setBoxes == true ) {
            //makes them the opposite of what they should be so we can build boxes by checking if there is a bigger max or smaller min
            this.box.x2 = Double.NEGATIVE_INFINITY;

            this.box.x1 = Double.POSITIVE_INFINITY;
            this.box.y2 = Double.NEGATIVE_INFINITY;
            this.box.y1 = Double.POSITIVE_INFINITY;
            this.box.z2 = Double.NEGATIVE_INFINITY;
            this.box.z1 = Double.POSITIVE_INFINITY;
        }
        _geometries = new ArrayList<>();//creates a new list for intersectable geometries
    }

    /**
     * adds new shapes to the geometry list and sets the bounding boxes is required
     * @param geometries - list of intersectable geometries
     */
    public void add(Intersectable... geometries)
    {

        for (Intersectable geo : geometries )// adds each of the geometries the function received to to the list of intersectable geometries
        {
            _geometries.add(geo);
            //sets the bounding boxes between shapes - if there is a bigger max value of smaller min value for one of the coordinates than what we already have we will switch it
            if(this.setBoxes==true)
            {
                if (geo.box.x2 > this.box.x2)
                    this.box.x2 = geo.box.x2;
                if (geo.box.x1 < this.box.x1)
                    this.box.x1 = geo.box.x1;
                if (geo.box.y2 > this.box.y2)
                    this.box.y2 = geo.box.y2;
                if (geo.box.y1 < this.box.y1)
                    this.box.y1 = geo.box.y1;
                if (geo.box.z2 > this.box.z2)
                    this.box.z2 = geo.box.z2;
                if (geo.box.z1 < this.box.z1)
                    this.box.z1 = geo.box.z1;

            }
     //      _geoList.add((Geometries) _geometries);
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
                if ((this.setBoxes == true && geo.intersects(ray)) || this.setBoxes == false) {
                    List<GeoPoint> tempIntersections = geo.findIntersections(ray);
                    if (tempIntersections != null) //if intersection points were found we need to add them to the list
                    {
                        intersections.addAll(tempIntersections);
                    }
                }
            }
            if (intersections.size() == 0)//if none of the shapes have intersection points with the ray null is returned
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

    /**
     * recursive function to check for intersections with the bvh tree
     * @param ray the ray we are checking for an intersection with
     * @return list of intersection points
     */
    public List<GeoPoint> bvhTree(Ray ray)
    {
        if(this._geoList.size()==0)//if there are no boxes left to check
        {
            return this.findIntersections(ray);//we need to check for an intersection with the geometry
        }
        else
            {
        if(this.intersects(ray))// if there is an intersection with a ray
        {
            for(Geometries geo : this._geoList)// for each geometry in the box that we found an intersection with
            {
                geo.bvhTree(ray);//recursive call
            }
        }
    }
        return null;
    }



}
