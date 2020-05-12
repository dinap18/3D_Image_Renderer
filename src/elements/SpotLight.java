package elements;
import primitives.*;
/**
 * spot light class that extends point light class
 */
public class SpotLight extends PointLight  {
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
     *  Constructor of spot light class
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
        double projection = _direction.dotProduct(getL(p));

        if (Util.isZero(projection)) {
            return Color.BLACK;
        }
        double factor = Math.max(0, projection);
        Color pointlightIntensity = super.getIntensity(p);

        if (_concentration != 1) {
            factor = Math.pow(factor, _concentration);
        }

        return (pointlightIntensity.scale(factor));
    }
}

