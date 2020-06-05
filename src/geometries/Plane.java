package geometries;

import primitives.Material;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Class Plane implements the geometry interface
 */
public class Plane extends Geometry
{
    /**
     *fields of class Plane
     */
    Point3D _p;
    Vector _normal;
    /**
     * Constructor Plane with three Point3D
     * @param p1 point1
     * @param p2 point2
     * @param p3 point3
     * @param emissionLight emission light color
     * @param material material
     *
     */
    public Plane(Color emissionLight, Material material, Point3D p1, Point3D p2, Point3D p3)
    {
        super(emissionLight, material);// calls flat geometry(and geometry) constructor to deal with emission light from the shape and the material

        //creates vectors between points to help us find the normal vector
        Vector U = new Vector(p2.subtract(p1));
        Vector V = new Vector(p3.subtract(p1));
        Vector N = U.crossProduct(V);//the cross product between the two vectors is the normal vector
        N.normalize();//normalizing the normal vector

        _normal = N.scale(-1);//scaling the normal by -1
        _p=p1;//assigning a point to the plane for reference
    }

    /**
     * Constructor Plane with Point3D and vector normal
     * @param _p-point3D
     * @param _normal-vector
     */
    public Plane(Point3D _p, Vector _normal) {
        super(Color.BLACK, new Material(0, 0, 0));//calls  geometry to assign black as emission color and the material

        this._p = _p;
        this._normal = _normal;
    }

    /**
     * constructor that uses a point,normal, color, and material to build a plane
     * @param emission - the plane's color
     * @param material the plane's material
     * @param _p plane's point3D
     * @param _normal plane's normal
     */
    public Plane(Color emission,Material material ,Point3D _p, Vector _normal) {
        super(emission,material);//calls  geometry to assign black as emission color and the material

        this._p = _p;
        this._normal = _normal;
    }
    /**
     * constructor
     * @param emissionLight emission light color
     * @param p1 point 1
     * @param p2 point 2
     * @param p3 point 3
     */
    public Plane(Color emissionLight, Point3D p1, Point3D p2, Point3D p3) {
        this(emissionLight, new Material(0, 0, 0), p1, p2, p3);
    }

    /**
     * constructor
     * @param p1 point 1
     * @param p2 point 2
     * @param p3 point 3
     */
    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        this(Color.BLACK, p1, p2, p3);
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

    /**
     * finds intersections between a ray and a plane
     * @param ray the ray we are looking for intersections with
     * @return list of Point3Ds that intersect with the plane
     */
    @Override
    public List<geometries.Intersectable.GeoPoint> findIntersections(Ray ray) {
        Vector v;
        try {
            v= _p.subtract(ray.get_p0());
        } catch (IllegalArgumentException e) {
            return null; // ray starts from point Q - no intersections
        }

        double nv = _normal.dotProduct(ray.get_dir());
        if (isZero(nv)) // ray is parallel to the plane - no intersections
            return null;

        double t = alignZero(_normal.dotProduct(v) / nv);
             if(t<=0)//there are no intersections
             return null;
        Intersectable.GeoPoint geo = new Intersectable.GeoPoint(this, ray.getTargetPoint(t));
        return List.of(geo);//returns list of geopoint intersections
    }
}
