package primitives;

import java.util.Objects;

public class Vector {
    Point3D _head;

    public Vector(Point3D _head) {
        this._head = _head;
    }
  /*  public Vector(Point3D _p1, Point3D _p2) {
        this._head= _p2.subtract(_p1);
    }*/

    public Vector(double x,double y,double z)
    {
        this._head._x=new Coordinate(x);
        this._head._y=new Coordinate(y);
        this._head._z=new Coordinate(z);
    }
    public Vector(Coordinate x,Coordinate y,Coordinate z)
    {

    }
    public Vector(Vector v)
    {
        new Vector(v._head._x,v._head._y,v._head._z);
    }
    public Point3D get_head() {
        return new Point3D(_head._x,_head._y,_head._z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return _head.equals(vector._head);
    }


    public Vector subtract(Vector v)
    {
      return this._head.subtract(v._head);

    }
    public Vector add(Vector v)
    {
        return new Vector( this._head.add(v));
    }
    public Vector scale(double d)
    {
        return new Vector(this._head._x.get()*d,this._head._y.get()*d,this._head._z.get()*d);
    }
    public double dotProduct(Vector v)
    {
        double dp;
        dp = (this._head.get_x().get()*(v._head.get_x().get()))
                + (this._head.get_y().get()*(v._head.get_y().get()))
                + (this._head.get_z().get()*(v._head.get_z().get()));
        return dp;
    }
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
    public double lengthSquared()
    {
        return this.length()*this.length();
    }
    public double length()
    {
        double lgth;
        lgth = Math.pow(this._head.get_x().get(),2) +
                Math.pow(this._head.get_y().get(),2) +
                Math.pow(this._head.get_z().get(), 2);
        lgth = Math.sqrt(lgth);
        return lgth;
    }
    public Vector normalize()
    {
        double l= this.length();
        this._head=((this._head.get_x().get()/l));
        this._head.get_y()=(this._head.get_y().get()/l);
        this._head.get_z()=(this._head.get_z().get()/l);
    }
    public Vector normalized()
    {

    }

}
