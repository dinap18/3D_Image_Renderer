package geometries;

import primitives.*;

import java.util.List;

/**
 * class Cylinder
 */
public class Cylinder extends Tube
{
    /**
     * cylinder height
     */
    double _height;

    /**
     * Constructor of Cylinder
     * @param _radius double radius
     * @param _axisRay ray
     * @param _height double
     */
    public Cylinder(double _radius, Ray _axisRay, double _height)
    {
        super(_radius, _axisRay);//calls the tube constructor
        this._height = _height;
    }


    /**
     * Function getNormal
     * @param p=Point3D
     * @return normal vector to the cylinder
     */
    public Vector getNormal(Point3D p)
    {
        Vector u = p.subtract(this._axisRay.get_p0());//subtracts the beginning of the axis ray from point3D p
        double t =this._axisRay.get_dir().dotProduct(u);//dot product between the axis ray direction and the distance between the beginning of the axis ray from point3D p
        Point3D point = this._axisRay.get_p0().add(this._axisRay.get_dir().scale(t));//scales the direction vector with v and adds it to the begging point of the axis ray
        Vector n = new Vector(p.subtract(point));//subtracts the new point we created from the point received in the function and creates a new vector for it
        return n.normalize();//returns the new vector normalized

    }

    /**
     * Function get_height
     * @return height of cylinder
     */
    public double get_height() {
        return _height;
    }

    /**
     * Function ToString
     * @return string with tostring of Tube and height
     */
    @Override
    public String toString() {
        return super.toString()+"height=" + _height ;
    }

    /**
     * finds intersections of a cylinder and a ray
     * @param ray
     * @return list of the intersected points
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        return super.findIntersections(ray);// calls the tube find intersections
    }
}
