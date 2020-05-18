package elements;

import primitives.Color;

/**
 * abstract Light Class
 */
abstract class Light {
    /**
     * field for class light- the color of the light's intensity
     */
    protected Color _intensity;



    /**
     * function Get light color intensity
     * @return intensity color
     */
    public Color getIntensity() {
        return new Color(_intensity);
    }
}
