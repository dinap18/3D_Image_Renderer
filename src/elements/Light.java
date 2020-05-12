package elements;

import primitives.Color;

/**
 * abstract Light Class
 */
abstract class Light {
    protected Color _intensity;



    /**
     * function Get light color intensity
     * @return intensity color
     */
    public Color getIntensity() {
        return new Color(_intensity);
    }
}
