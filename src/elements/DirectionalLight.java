package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * directional light class that extends the light class and implements the light source interface
 */
public class DirectionalLight extends Light implements LightSource  {
    /**
     * field of class directional light- the direction of the light
     */
    private Vector _direction;
    /**
     * Constructor for directional light
     *
     * @param _intensity color
     * @param direction  vector to be normalized
     */
    public DirectionalLight(Color _intensity, Vector direction) {
        this._intensity = _intensity;
        _direction = direction.normalized();
    }

    /**
     * get color intensity function
     * @param p point3D - not used
     * @return fixed intensity of the directional light
     */
    @Override
    public Color getIntensity(Point3D p) {
        return super.getIntensity();
    }

    /**
     * get direction function
     * @param p Point3D
     * @return direction vector
     */
    @Override
    public Vector getL(Point3D p) {
        return _direction;
    }

    /**
     * returns distance between light and a point3D
     * @param point Point3D
     * @return double distance
     */
    @Override
    public double getDistance(Point3D point)
    {
        return Double.POSITIVE_INFINITY;// like the distance between the sun and the earth the distance is to far to calculate
    }
}
