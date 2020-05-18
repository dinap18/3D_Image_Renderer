package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * point light class that extends the light class and implements the light source interface
 */
public class PointLight  extends Light implements LightSource {
    /**
     *
     */
    Point3D _position;
    double _kC; // Constant attenuation
    double _kL; // Linear attenuation
    double _kQ; // Quadratic attenuation

    /**
     * copy Constructor of point light class
     * @param colorIntensity the color intensity
     * @param position the position
     * @param kC constant attenuation
     * @param kL linear attenuation
     * @param kQ quadratic attenuation
     */
    public PointLight(Color colorIntensity, Point3D position, double kC, double kL, double kQ) {
        this._intensity = new Color(colorIntensity);
        this._position = new Point3D(position);
        this._kC = kC;
        this._kL = kL;
        this._kQ = kQ;
    }

    /**
     * Constructor of point light class- default kc,kl,kq
     * @param colorIntensity the color intensity
     * @param position the position- Point3D
     */
    public PointLight(Color colorIntensity, Point3D position) {
        this(colorIntensity, position, 1d, 0d, 0d);
    }

    /**
     *  Get intensity Function
     * @return color
     */
    @Override
    public Color getIntensity() {
        return super.getIntensity();
    }

    /**
     * Get intensity function
     * @param p point3D
     * @return color
     */
    @Override
    public Color getIntensity(Point3D p) {
        double dsquared = p.distanceSquared(_position);//distance between the point and the position of the spot light squared
        double d = p.distance(_position);//distance between the point and the position of the spot light
        return (_intensity.reduce(_kC + _kL * d + _kQ * dsquared));
    }

    /**
     * Get light function
     * @param p Point3D
     * @return light vector
     */
    @Override
    public Vector getL(Point3D p) {
        if (p.equals(_position))//if the point and the position of the light are the same there is no distance
        {
            return null;
        }
       else
           return p.subtract(_position).normalize();//finds the distance between the point and the position of the light and normalizes it
    }

    /**
     * returns distance between point light and point3D
     * @param point Point3D
     * @return double distance
     */
    @Override
    public double getDistance(Point3D point) {
        return _position.subtract(point).length();// subtract the point position from  the point the function received and find the length of that vector
    }

}

