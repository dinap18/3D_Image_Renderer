package primitives;
import static java.lang.Math.*;
/**
 *  Point#d basic point with coordinates in X axis, Y axis and Z axis
 */
public class Point3D {


        /**
         * Coordinates of Point3D
         */
        Coordinate _x;
        Coordinate _y;
        Coordinate _z;

    public void set_x(Coordinate _x) {
        this._x = _x;
    }

    public void set_y(Coordinate _y) {
        this._y = _y;
    }

    public void set_z(Coordinate _z) {
        this._z = _z;
    }

    public final static Point3D ZERO = new Point3D(0.0,0.0,0.0);
        /**
         *
         * @param _x x coordinate
         * @param _y y coordinate
         * @param _z z coordinate
         */
        public Point3D(Coordinate _x, Coordinate _y, Coordinate _z) {
            this._x = _x;
            this._y = _y;
            this._z = _z;
        }

        public Point3D(double _x, double _y, double _z) {
            this(new Coordinate(_x),new Coordinate(_y),new Coordinate(_z));
        }

    /**
     * copy constructor
     * @param p
     */
    public Point3D(Point3D p) {
        new Point3D(new Coordinate(p._x),new Coordinate(p._y),
        _z = new Coordinate(p._z));
    }

    /**
         *
         * @return new Coordinate based on _x
         */
        public Coordinate get_x() {
            return new Coordinate(_x);
        }

        public Coordinate get_y() {
            return new Coordinate(_y);
        }

        public Coordinate get_z() {
            return new Coordinate(_z);
        }

        /**
         * TODO
         * @param o
         * @return
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point3D point3D = (Point3D) o;
            return _x.equals(point3D._x) &&
                    _y.equals(point3D._y) &&
                    _z.equals(point3D._z);
        }

        @Override
        public String toString() {
            return "(" +
                    _x +
                    ", " + _y +
                    ", " + _z +
                    ')';
        }
        public Vector subtract(Point3D p) {
            return new Vector(new Point3D(
                    p._x.get()- this._x.get(),
                    p._y.get()- this._y.get(),
                    p._z.get()- this._z.get()
            ));
        }
        public Point3D add(Vector v)
        {
            return new Point3D(
                   v._head._x.get()+ this._x.get(),
                    v._head._y.get()+ this._y.get(),
                    v._head._z.get()+ this._z.get());

        }
        public double distanceSquared(Point3D p)
        {
            return (_x.get()-(p._x).get())*(_x.get()-(p.get_x()).get())+
                    (_y.get()-(p.get_y()).get())*(_y.get()-(p.get_y()).get())
                    +(_z.get()-(p._z).get())*(_z.get()-(p._z).get());
        }
        public double distance(Point3D p)
        {
            return Math.sqrt((_x.get()-(p._x).get())*(_x.get()-(p.get_x()).get())+
                    (_y.get()-(p.get_y()).get())*(_y.get()-(p.get_y()).get())
                    +(_z.get()-(p._z).get())*(_z.get()-(p._z).get()));
        }
}
