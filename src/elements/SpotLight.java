package elements;
import primitives.*;
/**
 * spot light class that extends point light class
 */
public class SpotLight extends PointLight  {
    /**
     * fields for spot light
     */
    Vector _direction;
    double _concentration;

    /**
     *  Constructor of spot light class
     * @param colorIntensity the color intensity
     * @param position the position - point3D
     * @param direction the director direction
     * @param kC the constant attenuation
     * @param kL the linear attenuation
     * @param kQ the Quadratic attenuation
     * @param concentration how quickly the light intensity attenuates
     */
    public SpotLight(Color colorIntensity, Point3D position, Vector direction, double kC, double kL, double kQ, double concentration) {
        super(colorIntensity, position, kC, kL, kQ);
        this._direction = new Vector(direction).normalized();
        this._concentration = concentration;
    }

    /**
     *  Constructor of spot light class with default concentration
     * @param colorIntensity the color intensity
     * @param position the position - point3D
     * @param direction the director direction
     * @param kC the constant attenuation
     * @param kL the linear attenuation
     * @param kQ the Quadratic attenuation
     */
    public SpotLight(Color colorIntensity, Point3D position, Vector direction, double kC, double kL, double kQ) {
        this(colorIntensity, position, direction, kC, kL, kQ, 1);
    }


    /**
     * @param p Point3D
     * The get intensity function
     * @return spotlight intensity
     */
    @Override
    public Color getIntensity(Point3D p) {
        double projection = _direction.dotProduct(getL(p));//dot product between the direction vector and the direction from point p

        if (Util.isZero(projection)) // if the projection distance is zero8
        {
            return Color.BLACK;
        }
        double factor = Math.max(0, projection);//the factor is zero if the projection is zero , else it is the projection value(double)
        Color pointlightIntensity = super.getIntensity(p);//the intensity of Point3D p

        if (_concentration != 1)
        {
            factor = Math.pow(factor, _concentration);//if the concentration isnt 1 we put the factor to the power of concentration
        }

        return (pointlightIntensity.scale(factor));//returns the intensity scaled by the projection factor
    }

}

