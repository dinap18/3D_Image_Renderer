package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * The abstract class RadialGeometry
 */
public abstract class RadialGeometry implements Geometry
{

    /**
     * Fields (radius)
     */
    double _radius;
    /**
     * ToString Function
     * @return string with the radius
     */
    @Override
    public String toString() {
        return " radius=" + _radius;
    }

    /**
     * Constructor of the class RadialGeometry
     * @param _radius
     */
    public RadialGeometry(double _radius)
    {
        this._radius = _radius;
    }

    /**
     *  Constructor of the class RadialGeometry
     * @param r-radius
     */
    public RadialGeometry(RadialGeometry r)
    {
        this._radius=r._radius;
    }

    /**
     * getRadius Fonction
     * @return radius
     */
    public double get_radius()
    {
        return _radius;
    }
}
