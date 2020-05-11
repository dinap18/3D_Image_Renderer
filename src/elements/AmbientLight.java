package elements;
import primitives.Color;

/**
 * ambient light class
 */
public class AmbientLight {
    /**
     *get color intensity function
     * @return color intensity
     */
    public Color getIntensity() {
        return _intensity;
    }

    private Color _intensity;

    /**
     * set intensity function
     * @param _intensity color intensity
     */
    public void setIntensity(Color _intensity) {
        this._intensity = _intensity;
    }

    /**
     * constructor for ambient light class
     * @param intensity color intensity
     * @param kA always equals 1
     */
    public AmbientLight(Color intensity, double kA) {
        _intensity = intensity.scale(kA);
    }

    public AmbientLight(Color iA) {
        this(iA, 1.d);
    }


}
