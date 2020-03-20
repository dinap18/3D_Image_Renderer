
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
     * @param x-double
     * @param y-double
     * @param z-double
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
     * @param x-coordinate
     * @param y-coordinate
     * @param z-coordinate
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
     * @param v-Vector
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
     * @param o-vector
     * @return true if the vectors are equal
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
     * @param v-Vector
     * @return vector- subtracts one vector from the other and returns the result
     */
    public Vector subtract(Vector v)
    {
      return new Vector(this._head.subtract(v._head));

    }

    /**
     * add function
     * @param v-Vector
     * @return vector- adds to vectors together and returns the result
     */
    public Vector add(Vector v)
    {
        return new Vector( this._head.add(v));
    }

    /**
     * scale function
     * @param d-double
     * @return vector- returns a vector multiplied by a scalar=a*v=(a*v1,a*v2,a*v3)
     */
    public Vector scale(double d)
    {
        return new Vector(this._head._x.get()*d,this._head._y.get()*d,this._head._z.get()*d);
    }

    /**
     * dot product function
     * @param v-Vector
     * @return double-returns the dot product of two vectors=𝑢 ∙ 𝑣 = 𝑢1 ∙ 𝑣1 + 𝑢2 ∙ 𝑣2 + 𝑢3 ∙ 𝑣3
     */
    public double dotProduct(Vector v)
    {

        return (this._head.get_x().get()*(v._head.get_x().get()))
                + (this._head.get_y().get()*(v._head.get_y().get()))
                + (this._head.get_z().get()*(v._head.get_z().get()));

    }

    /**
     * cross product function
     * @param v-Vector
     * @return vector-returns the cross product of two vectors=u*v=(𝑢2 ∙ 𝑣3 − 𝑢3 ∙ 𝑣2,𝑢3 ∙ 𝑣1 − 𝑢1 ∙ 𝑣3,𝑢1 ∙ 𝑣2 − 𝑢2 ∙ 𝑣1)
     */
    public Vector crossProduct(Vector v)
    {
        double x = (this._head.get_y().get()*(v._head.get_z().get()))-(this._head.get_z().get()*(v._head.get_y().get()));
        double y = (this._head.get_z().get()*(v._head.get_x().get()))-(this._head.get_x().get()*(v._head.get_z().get()));
        double z = (this._head.get_x().get()*(v._head.get_y().get()))-(this._head.get_y().get()*(v._head.get_x().get()));
        Coordinate x1 = new Coordinate(x);
        Coordinate y1 = new Coordinate(y);
        Coordinate z1 = new Coordinate(z);
        return new Vector( new Point3D(x1,y1,z1));
    }

    /**
     * vector length squared function
     * @return double-length of vector squared
     */
    public double lengthSquared()
    {
        return this.length()*this.length();
    }

    /**
     * vector length function
     * @return double-length of vector
     */
    public double length()
    {
        double lgth = Math.pow(_head.get_x().get(),2) +
                Math.pow(_head.get_y().get(),2) +
                Math.pow(_head.get_z().get(), 2);
        lgth = Math.sqrt(lgth);
        return lgth;
    }

    /**
     * normalize vector function
     * @return vector-normalized vector
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
     * @return vector-new normalized vector
     */
    public Vector normalized()
    {
        return new Vector(this.normalize());
    }

}
