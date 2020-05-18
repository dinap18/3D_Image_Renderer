package geometries;

import primitives.*;
/**
 * abstract class flat geometry that extends Geometry
 */
public abstract class FlatGeometry extends Geometry {
    public FlatGeometry(Color _emission, Material _material) {
        super(_emission, _material);
    }

    /**
     * constructor for abstract class flat geometry
     * @param _emission color
     */
    public FlatGeometry(Color _emission) {
        super(_emission);// calls Geometry constructor to set emission
    }

    /**
     * default constructor for flat geometry
     */
    public FlatGeometry() {
        super();//calls the Geometry default constructor
    }
}
