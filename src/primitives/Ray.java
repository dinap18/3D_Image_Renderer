package primitives;

public class Ray
{
    Point3D _p0;
    Vector _dir;
    public Ray(Point3D p0, Vector dir)
    {
        this._p0 = new Point3D(p0);
        this._dir = new Vector (dir);
        if(_dir.length()!=1.0)
        {
            this._dir.normalize();
        }
    }
    public Ray(Ray r)
    {
        this._p0 = new Point3D(r._p0);
        this._dir = new Vector (r._dir);
        if(_dir.length()!=1.0)
        {
            this._dir.normalize();
        }
    }
    public Point3D get_p0() {
        return _p0;
    }

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
    @Override
    public String toString()
    {
        return "starting point"+_p0.toString()+" direction:" + _dir.toString();
    }
}
