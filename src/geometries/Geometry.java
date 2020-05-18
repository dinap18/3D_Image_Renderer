package geometries;

import primitives.Material;
import primitives.*;

/**
 * interface of geometries
 */
public abstract class Geometry implements Intersectable
{

    /**
     * fields for abstract class Geometry - the emission color and the material
     */
    protected Color _emission;
    Material _material;

    /**
     * get normal function
     * @param p point3D
     * @return the normal vector from the shape to the point3D the function received
     */
    public abstract Vector getNormal(Point3D p);
    /**
     * returns the emission color
     * @return emission color
     */
    public Color get_emission() {
        return _emission;
    }

    /**
     * default constructor for class geometry
     */
    public Geometry() {
        this(Color.BLACK);

    }

    /**
     * 2 parameter constructor for class geometry
     * @param _emission
     * @param _material
     */
    public Geometry(Color _emission, Material _material) {
        this._emission = _emission;
        this._material = _material;
    }

    /**
     * get material function
     * @return material
     */
    public Material get_material() {
        return _material;
    }

    /**
     * constructor for abstract geometry class - with default material values
     * @param _emission emission color
     */
    public Geometry(Color _emission) {
        this(_emission,new Material(0d,0d,0));
    }

}
