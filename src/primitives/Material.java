package primitives;

/**
 * material class
 */
public class Material {
    /**
     * fields for class material
     */
    double _kD;
    double _kS;
    int _nShininess;
    double _kT;
    double _kR;

    /**
     * get transparency
     * @return double - the amount of transparency
     */
    public double get_kT() {
        return _kT;
    }

    /**
     * get reflection
     * @return double - the amount of reflection
     */
    public double get_kR() {
        return _kR;
    }

    /**
     * constructor for class material- all parameters
     * @param _kD double
     * @param _kS double
     * @param _nShininess int
     * @param _kT double-  transparency
     * @param _kR double - reflection
     */
    public Material(double _kD, double _kS, int _nShininess, double _kT, double _kR) {
        this._kD = _kD;
        this._kS = _kS;
        this._nShininess = _nShininess;
        this._kT = _kT;
        this._kR = _kR;
    }

    /**
     * three parameter constructor for class material- gives default values to kt and kr
     * @param _kD double
     * @param _kS double
     * @param _nShininess int
     */
    public Material(double _kD, double _kS, int _nShininess) {
      this(_kD,_kS,_nShininess,0,0);
    }

    /**
     * one parameter constructor for class material
     * @param material material
     */
    public Material(Material material) {
        this(material._kD, material._kS, material._nShininess,material._kT,material._kR);
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
