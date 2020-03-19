package geometries;

import primitives.*;

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
     * @param _radius
     * @param _axisRay
     * @param _height
     */
    public Cylinder(double _radius, Ray _axisRay, double _height)
    {
        super(_radius, _axisRay);
        this._height = _height;
    }


    /**
     * Function getNormal
     * @param p
     * @return
     */
    public Vector getNormal(Point3D p)
    {
        return null;
    }

    /**
     * Function get_height
     * @return
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
}
