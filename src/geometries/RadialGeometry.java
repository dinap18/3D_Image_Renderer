package geometries;


import primitives.*;

import static primitives.Util.isZero;

/**
 * The abstract class RadialGeometry
 */
public abstract class RadialGeometry extends Geometry
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
     * @param radius double
     *  @param material Material
     * @param emissionLight emission light color
     */
    public RadialGeometry(Color emissionLight, double radius, Material material) {
        super(emissionLight, material);//calls geometry constructor to assign the emission light color and the material
        if (isZero(radius) || (radius < 0.0))//makes sure that the radius isnt negative or zero
            throw new IllegalArgumentException("radius " + radius + " is not valid");
        this._radius = radius;
    }
    /**
     *  Constructor of the class RadialGeometry
     * @param r-radius
     */
    public RadialGeometry(RadialGeometry r) {
        super(r._emission, r._material);
        // we can assume that other._radius is valid
        this._radius = r._radius;
    }

    /**
     * constructor for RadialGeometry
     * @param emissionLight emission light color
     * @param radius double radius
     */
    public RadialGeometry(Color emissionLight, double radius) {
        this(emissionLight, radius, new Material(0, 0, 0));
    }

    /**
     * getRadius Function
     * @return radius
     */
    public double get_radius()
    {
        return _radius;
    }
}
