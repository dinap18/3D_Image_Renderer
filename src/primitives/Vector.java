
package primitives;

import java.util.Objects;
   /**
         *Class Vector
   */
public class Vector
{
    /**
         *Fields (point3D)
   */
    Point3D _head;

    /**
     * Vector constructor
     * @param _head - Point3D that is the vector's head
     */
    public Vector(Point3D _head)
    {
        if(_head.equals(Point3D.ZERO))
        {
            throw new IllegalArgumentException("vector cannot be the zero vector");
        }
        this._head = _head;
    }

    /**
     * Vector Constructor
     * @param x
     * @param y
     * @param z
     */
    public Vector(double x,double y,double z)
    {
        if(new Point3D(new Coordinate(x),new Coordinate(y),new Coordinate(z)).equals(Point3D.ZERO))
        {
            throw new IllegalArgumentException("vector cannot be the zero vector");
        }
        this._head=new Point3D(new Coordinate(x),new Coordinate(y),new Coordinate(z));
}


    /**
     * Vector constructor
     * @param x
     * @param y
     * @param z
     */
    public Vector(Coordinate x,Coordinate y,Coordinate z)
    {
        if(new Point3D(x,y,z).equals(Point3D.ZERO))
        {
            throw new IllegalArgumentException("vector cannot be the zero vector");
        }
        this._head=new Point3D(x,y,z);
    }

    /**
     * copy constructor
     * @param v
     */
    public Vector(Vector v)
    {
        this._head = v._head;
    }

    /**
     * get head
     * @return Point3D which is the vector's head
     */
    public Point3D get_head()
    {
        return new Point3D(_head._x,_head._y,_head._z);
    }

    /**
     * equals
     * @param o
     * @return true if they are equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Vector vector = (Vector) o;
        return _head.equals(vector._head);
    }

    /**
     * subtract function
     * @param vector
     * @return vector
     */
    public Vector subtract(Vector v)
    {
      return this._head.subtract(v._head);

    }

    /**
     * add function
     * @param vector
     * @return vector
     */
    public Vector add(Vector v)
    {
        return new Vector( this._head.add(v));
    }

    /**
     * scale function
     * @param double
     * @return vector
     */
    public Vector scale(double d)
    {
        return new Vector(this._head._x.get()*d,this._head._y.get()*d,this._head._z.get()*d);
    }

    /**
     * dot product function
     * @param vector
     * @return double
     */
    public double dotProduct(Vector v)
    {
        double dp;
        dp = (this._head.get_x().get()*(v._head.get_x().get()))
                + (this._head.get_y().get()*(v._head.get_y().get()))
                + (this._head.get_z().get()*(v._head.get_z().get()));
        return dp;
    }

    /**
     * cross product function
     * @param vector
     * @return vector
     */
    public Vector crossProduct(Vector v)
    {
        double x = (this._head.get_y().get()*(v._head.get_z().get()))-(this._head.get_z().get()*(v._head.get_y().get()));
        double y = (this._head.get_z().get()*(v._head.get_x().get()))-(this._head.get_x().get()*(v._head.get_z().get()));
        double z = (this._head.get_x().get()*(v._head.get_y().get()))-(this._head.get_y().get()*(v._head.get_x().get()));
        Coordinate x1 = new Coordinate(x);
        Coordinate y1 = new Coordinate(y);
        Coordinate z1 = new Coordinate(z);
        Point3D p3D = new Point3D(x1,y1,z1);
        Vector cp = new Vector(p3D);
        return cp;
    }

    /**
     * vector length squared function
     * @return double
     */
    public double lengthSquared()
    {
        return this.length()*this.length();
    }

    /**
     * vector length function
     * @return double
     */
    public double length()
    {
        double lgth;
        lgth = Math.pow(_head.get_x().get(),2) +
                Math.pow(_head.get_y().get(),2) +
                Math.pow(_head.get_z().get(), 2);
        lgth = Math.sqrt(lgth);
        return lgth;
    }

    /**
     * normalize vector function
     * @return vector
     */
    public Vector normalize()
    {
        double l= this.length();
        this._head._x=new Coordinate(this.get_head()._x.get()/l);
        this._head._y=new Coordinate(this.get_head()._y.get()/l);
        this._head._z=new Coordinate(this.get_head()._z.get()/l);
        return this;
    }
    
    /**
     * normalized vector function
     * @return vector
     */
    public Vector normalized()
    {
        return new Vector(this.normalize());
    }

}
