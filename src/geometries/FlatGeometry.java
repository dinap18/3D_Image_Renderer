package geometries;

import primitives.*;
/**
 * abstract class that extends Geometry
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
        super(_emission);
    }

    /**
     * default constructor for flat geometry
     */
    public FlatGeometry() {
        super();
    }
}
