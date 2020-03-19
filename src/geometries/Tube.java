package geometries;

import primitives.*;

/**
 * class Tube
 */
public class Tube extends RadialGeometry
{
    /**
     * fields (axisRay)
     */
    Ray _axisRay;

    /**
     * Constructor of the tube class
     * @param _radius
     * @param _axisRay
     */
    public Tube(double _radius, Ray _axisRay)
    {
        super(_radius);
        this._axisRay = _axisRay;
    }


    @Override
    public Vector getNormal(Point3D p) {
        return null;
    }
}
