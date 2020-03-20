package primitives;
import static java.lang.Math.*;
/**
 *  Point3d basic point with coordinates in X axis, Y axis and Z axis
 */
public class Point3D {


        /**
         * Coordinates of Point3D
         */
        Coordinate _x;
        Coordinate _y;
        Coordinate _z;


    public final static Point3D ZERO = new Point3D(0.0,0.0,0.0);
        /**
         *
         * @param _x x coordinate
         * @param _y y coordinate
         * @param _z z coordinate
         */
        
          /**
         * Constructor of the class with three coordinates
         */
        public Point3D(Coordinate _x, Coordinate _y, Coordinate _z)
        {

            this._x = _x;
            this._y = _y;
            this._z = _z;

        }

         /**
         * Constructor of the class with three double
         */
        public Point3D(double _x, double _y, double _z)
        {
            this._x=new Coordinate(_x) ;
            this._y=new Coordinate(_y);
            this._z=new Coordinate(_z);
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
         *Get functions
         * @return new Coordinate based on _x , _y, or _z
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
         *
         * @param o
         * @return true if the points are equal
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

         /**
         * ToString function
          * @return the point3D as a string
         */
        
        @Override
        public String toString() {
            return "(" +
                    _x +
                    ", " + _y +
                    ", " + _z +
                    ')';
        }
        
         /**
         *Subtract function
          * @param p
         * @return Vector
         */
        public Vector subtract(Point3D p) {
            return new Vector(new Point3D(
                    p._x.get()- this._x.get(),
                    p._y.get()- this._y.get(),
                    p._z.get()- this._z.get()
            ));
        }
        
            /**
         *Add function
          * @param v-vector
         * @return point3D- adds two vectors and returns the resulting Point3D
         */
        public Point3D add(Vector v)
        {
            return new Point3D(
                   v._head._x.get()+ this._x.get(),
                    v._head._y.get()+ this._y.get(),
                    v._head._z.get()+ this._z.get());

        }
            /**
         *DistanceSquared function
         * @param p-point3D
         * @return double - returns the distance between two points squared
         */
        public double distanceSquared(Point3D p)
        {
            return (_x.get()-(p._x).get())*(_x.get()-(p.get_x()).get())+
                    (_y.get()-(p.get_y()).get())*(_y.get()-(p.get_y()).get())
                    +(_z.get()-(p._z).get())*(_z.get()-(p._z).get());
        }
        
           /**
         *Distance function
         * @param p-point3D
         * @return double- returns the distance between two Point3Ds
         */
        public double distance(Point3D p)
        {
            return Math.sqrt((_x.get()-(p._x).get())*(_x.get()-(p.get_x()).get())+
                    (_y.get()-(p.get_y()).get())*(_y.get()-(p.get_y()).get())
                    +(_z.get()-(p._z).get())*(_z.get()-(p._z).get()));
        }
}
