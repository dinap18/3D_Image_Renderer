package geometries;

import primitives.*;

/**
 * interface of geometries
 */
public interface Geometry extends Intersectable
{
    public abstract Vector getNormal(Point3D p);
}
