
package primitives;

import java.security.cert.PolicyNode;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static primitives.Util.*;
import static primitives.Util.isZero;

/**
         *Class Ray
        
         */
public class Ray {
    /**
     * Fields (point3D,Vector)
     */
    private static double DELTA = 0.1;
    private final Point3D _p0;
    private final Vector _dir;

    /**
     * Constructor of Ray with point3D and vector in parameter
     *
     * @param p0-Point3D ,dir-Vector
     */
    public Ray(Point3D p0, Vector dir) {
        this._p0 = new Point3D(p0);
        this._dir = new Vector(dir).normalized();

    }

    /**
     * creates a new ray using 3 parameters
     *
     * @param p   point3D starting point of ray
     * @param dir vector - direction of ray
     * @param n   vector - normal to ray
     */
    public Ray(Point3D p, Vector dir, Vector n) {
        _dir = new Vector(dir).normalized();//normalizes the direction
        double dotProdNormal = n.dotProduct(dir);//n vector dot product the normalized direction vector
        Vector nDelta;
        if (dotProdNormal > 0)//if positive
            nDelta = n.scale(DELTA);
        else//we scale it with a negative
            nDelta = n.scale(-DELTA);
        _p0 = p.add(nDelta);//adds ndelta to the starting point
    }

    /**
     * Copy constructor of Ray
     *
     * @param r-Ray
     */
    public Ray(Ray r) {
        this._p0 = new Point3D(r._p0);
        this._dir = r._dir.normalized();
    }

    /**
     * Get point3D function
     */
    public Point3D get_p0() {
        return _p0;
    }

    /**
     * Get direction vector function
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
     *
     * @param length of the ray
     * @return ray's target point
     */
    public Point3D getTargetPoint(double length) {
        return Util.isZero(length) ? _p0 : _p0.add(_dir.scale(length));
    }

    /**
     * ToString function
     *
     * @return string with the starting point and the direction
     */
    @Override
    public String toString() {
        return "starting point" + this._p0.toString() + " direction:" + this._dir.toString();
    }

    /**
     * creates a beam of rays(in a list of rays)
     *
     * @param n        Vector - normal vector where the rays start
     * @param distance double - the distance between the  point and the circle we are creating to find the beam
     * @param num      int - the number of rays that will be in the beam
     * @return list that includes all the rays that make up the beam
     */
    public List<Ray> createBeamOfRays(Vector n, double distance, int num) {
        List<Ray> beam = new LinkedList<Ray>();
        beam.add(this);//the original ray that calls the function - there has to be at least one beam
        if (num == 1)//if no additional rays were requested here  there is nothing else to do in this function
            return beam;
        Vector w = this.get_dir().normalToVector();//finds a vector that is normal to the direction on the ray
        Vector v = this.get_dir().crossProduct(w).normalize();//the cross product between the normal and the direction

        Point3D center = this.getTargetPoint(distance);//the center of our circle is the distance requested from p0
        Point3D randomP = Point3D.ZERO;
        double xRandom, yRandom, random;
        double nDotDirection = alignZero(n.dotProduct(this.get_dir()));
        double r = Math.abs(Math.tan(Math.acos(w.dotProduct(v))));
        for (int i = 1; i < num; i++)//starts from 1 because there has to be at least one ray(the original)and we already dealt with it
        {
            xRandom = randomNumber(-1, 1);//random number [-1,1)
            yRandom = Math.sqrt(1 - Math.pow(xRandom, 2));
            random = randomNumber(-r, r);//random number[-r,r)
            if (xRandom != 0)//vector cannot be scaled with zero
                randomP = center.add(w.scale(random));
            if (yRandom != 0)//vector cannot be scaled with zero
                randomP = center.add(v.scale(random));
            Vector t = randomP.subtract(this.get_p0());//vector between the random point and the start of the original ray
            double normalDotT = alignZero(n.dotProduct(t));
            if (nDotDirection * normalDotT > 0)//if they are both positive or both negative then we need to create a ray with the original p0 and t
                beam.add(new Ray(this.get_p0(), t));
        }
        return beam;
    }
}