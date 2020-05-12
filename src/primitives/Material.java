package primitives;

/**
 * material class
 */
public class Material {
    double _kD;
    double _kS;
    int _nShininess;

    /**
     * three parameter constructor for class material
     * @param _kD double
     * @param _kS double
     * @param _nShininess int
     */
    public Material(double _kD, double _kS, int _nShininess) {
        this._kD = _kD;
        this._kS = _kS;
        this._nShininess = _nShininess;
    }

    /**
     * one parameter constructor for class material
     * @param material material
     */
    public Material(Material material) {
        this(material._kD, material._kS, material._nShininess);
    }

    /**
     * get kD function
     * @return double kD
     */
    public double getKd() {
        return _kD;
    }

    /**
     * get kS function
     * @return double kS
     */
    public double getKs() {
        return _kS;
    }

    /**
     * get nShininess function
     * @return int nShininess
     */
    public int getnShininess() {
        return _nShininess;
    }
}
