package elements;
import primitives.Color;

/**
 * ambient light class
 */
public class AmbientLight extends Light {


    /**
     * set intensity function
     * @param _intensity color intensity
     */
    public void setIntensity(Color _intensity) {
        this._intensity = _intensity;
    }

    /**
     * constructor for ambient light class -calls light constructor
     * @param intensity color intensity
     * @param kA
     */
    public AmbientLight(Color intensity,double kA) {

        _intensity=(intensity.scale(kA));
    }




}
