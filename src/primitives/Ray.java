
package primitives;

import java.security.cert.PolicyNode;

/**
         *Class Ray
        
         */
public class Ray
{
       /**
         *Fields (point3D,Vector)
         */
       private static double DELTA=0.1;
    private final Point3D _p0;
    private final Vector _dir;
       /**
         *Constructor of Ray with point3D and vector in parameter 
         * @param p0-Point3D ,dir-Vector
         */
    public Ray(Point3D p0, Vector dir)
    {
        this._p0 = new Point3D(p0);
        this._dir = new Vector(dir).normalized();

    }

    /**
     * creates a new ray using 3 parameters
     * @param p point3D starting point of ray
     * @param dir vector - direction of ray
     * @param n vector - normal to ray
     */
    public Ray(Point3D p, Vector dir,Vector n)
    {
        _dir=new Vector(dir).normalized();//normalizes the direction
        double dotProdNormal=n.dotProduct(dir);//n vector dot product the normalized direction vector
        Vector nDelta;
        if(dotProdNormal>0)//if positive
            nDelta=n.scale(DELTA);
        else//we make it positive
            nDelta=n.scale(-DELTA);
    _p0=p.add(nDelta);//adds ndelta to the starting point
    }
       /**
         *Copy constructor of Ray
         * @param r-Ray
         */
    public Ray(Ray r)
    {
        this._p0 = new Point3D(r._p0);
        this._dir = r._dir.normalized();
    }
    
       /**
         *Get point3D function
       */
    public Point3D get_p0() {
        return _p0;
    }
  /**
         *Get direction vector function
       */
    public Vector get_dir() {
        return _dir;
    }
   
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ray other = (Ray) obj;
        return _dir.equals(other._dir) && _p0.equals(other._p0);
    }

    /**
     * returns the target point of a ray
     * @param length of the ray
     * @return ray's target point
     */
    public Point3D getTargetPoint(double length) {
        return Util.isZero(length ) ? _p0 : _p0.add(_dir.scale(length));}
       /**
         *ToString function
         * @return string with the starting point and the direction
         */
    @Override
    public String toString()
    {
        return "starting point"+this._p0.toString()+" direction:" + this._dir.toString();
    }
}
