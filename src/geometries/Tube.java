package geometries;

import primitives.*;

import java.util.List;

/**
 * class Tube
 */
public class Tube extends RadialGeometry
{
    /**
     * fields (axisRay)
     */
     Ray _axisRay;

    /**
     * Constructor of the tube class
     * @param _radius the radius of the tube
     * @param _axisRay the axis ray of the tube
     */
    public Tube(double _radius, Ray _axisRay)
    {
        this(Color.BLACK, new Material(0, 0, 0), _radius, _axisRay);
    }

    /**
     *  constructor for class Tube
     * @param emissionLight the emission light
     * @param _material the material of the tube
     * @param _radius the tube's radius
     * @param _ray the axis ray of the tube
     */
    public Tube(Color emissionLight, Material _material, double _radius, Ray _ray) {
        super(Color.BLACK, _radius);
        this._material = _material;
        this._axisRay = new Ray(_ray);

    }

    /**
     * constructor for class Tube
     * @param emissionLight the emission light
     * @param _radius the radius of the tube
     * @param _ray the axis ray of the tube
     */
    public Tube(Color emissionLight, double _radius, Ray _ray) {
        this(emissionLight, new Material(0, 0, 0), _radius, _ray);
    }
 /**
     * getNormal function
     * @param p-point3D
     * @return normal vector
     */
    @Override
    public Vector getNormal(Point3D p) {
        Vector vector1 = p.subtract(this._axisRay.get_p0());//The vector from the point of the cylinder to the given point
         double projection = vector1.dotProduct(this._axisRay.get_dir());//We need the projection to multiply the _direction unit vector
         Vector vector2 = this._axisRay.get_dir().scale(projection);//scales the direction vector of the axis ray with the projection
          Vector check = vector1.subtract(vector2);
        return check.normalize();//normalizes the normal vector
    }

    /**
     * to string
     * @return string that describes a tube
     */
    @Override
    public String toString() {
        return super.toString()+
                "axisRay" + _axisRay

                ;
    }

    /**
     * finds the intersection points between a ray and a tube
     * @param ray
     * @return list of intersection points
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        return null;
    }
}
