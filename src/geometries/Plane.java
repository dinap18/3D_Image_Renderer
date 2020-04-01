package geometries;

import primitives.Point3D;
import primitives.Vector;

import java.awt.*;

/**
 * Class Plane implements the geometry interface
 */
public class Plane implements Geometry
{
    /**
     *fields of class Plane
     */
    Point3D _p;
    Vector _normal;
    /**
     * Constructor Plane with three Point3D
     * @param p1
     * @param p2
     * @param p3
     */
    public Plane (Point3D p1, Point3D p2, Point3D p3)
    {

        Vector U = new Vector(p2.subtract(p1));
        Vector V = new Vector(p3.subtract(p1));
        Vector N = U.crossProduct(V);
        N.normalize();

        _normal = N.scale(-1);
        _p=p1;
    }

    /**
     * Constructor Plane with Point3D and vector normal
     * @param _p-point3D
     * @param _normal-vector
     */
    public Plane(Point3D _p, Vector _normal) {
        this._p = _p;
        this._normal = _normal;
    }


    /**
     * getNormal Function
     * @param p-point3D
     * @return vector
     */
    @Override
    public Vector getNormal(Point3D p)
    {

        return _normal;
    }

    /**
     * Get Function
     * @return point3D
     */
    public Point3D get_p() {
        return _p;
    }

    /**
     * GetNormal Function
     * @return normal vector
     */
    public Vector get_normal() {
        return _normal;
    }

    /**
     * Function ToString
     * @return string with point and normal
     */
    @Override
    public String toString() {
        return  "point" + _p +  ", normal" + _normal;
    }
}
