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
     * @param _radius
     * @param _axisRay
     */
    public Tube(double _radius, Ray _axisRay)
    {
        super(_radius);
        this._axisRay = _axisRay;
    }

 /**
     * getNormal function
     * @param p-point3D
     * @return vector  
     */
    @Override
    public Vector getNormal(Point3D p) {
        //The vector from the point of the cylinder to the given point
        Vector vector1 = p.subtract(this._axisRay.get_p0());

        //We need the projection to multiply the _direction unit vector
        double projection = vector1.dotProduct(this._axisRay.get_dir());

        Vector vector2 = this._axisRay.get_dir().scale(projection);

        //This vector is orthogonal to the _direction vector.
        Vector check = vector1.subtract(vector2);
        return check.normalize();
    }

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
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }
}
