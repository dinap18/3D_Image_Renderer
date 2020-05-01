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
    public java.awt.Color getIntensity() {
        return _intensity.getColor();
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
     * @param _intensity color intensity
     * @param ka always equals 1
     */
    public AmbientLight(Color _intensity, double ka) {
        // ka is always 1 so we don't use it
        this._intensity = _intensity;
    }

}
