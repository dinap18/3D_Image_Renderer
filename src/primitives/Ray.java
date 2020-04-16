
package primitives;

   /**
         *Class Ray
        
         */
public class Ray
{
       /**
         *Fields (point3D,Vector)
         */
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
