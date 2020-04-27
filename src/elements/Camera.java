package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import static primitives.Util.isZero;

/**
 * class Camera
 */
public class Camera {
    Point3D _p0;
    Vector _vUp;
    Vector _vTo;
    Vector _vRight;

    /**
     * get p0 function
     * @return camera location point
     */
    public Point3D get_p0() {
        return new Point3D(_p0);
    }

    /**
     * get vUp function
     * @return camera up direction
     */
    public Vector get_vUp() {
        return new Vector(_vUp);
    }

    /**
     * get vTo function
     * @return camera towards direction
     */
    public Vector get_vTo() {
        return new Vector(_vTo);
    }

    /**
     * get vRight function
     * @return
     */
    public Vector get_vRight() {
        return new Vector(_vRight);
    }

    /**
     * constructor for class camera
     * @param _p0 - camera location
     * @param _vUp - camera up direction
     * @param _vTo - camera towards direction
     */
    public Camera(Point3D _p0, Vector _vTo, Vector _vUp)
    {

        if (_vUp.dotProduct(_vTo) != 0)
            throw new IllegalArgumentException("vUp and vTo need to be orthogonal");
        this._vUp = _vUp.normalized();
        this._vTo = _vTo.normalized();
        this._p0 =  new Point3D(_p0);
        _vRight = this._vTo.crossProduct(this._vUp).normalize();

    }

    /**
     * constructs a ray from camera location through pixel (i,j) in the view plane
     * @param nX  view plane row pixels
     * @param nY view plane column pixels
     * @param j row pixels
     * @param i column pixels
     * @param screenDistance distance from camera to view plane
     * @param screenWidth view plane width
     * @param screenHeight view plane height
     * @return the ray through the pixel's center
     */
    public Ray constructRayThroughPixel(int nX, int nY,int j, int i, double screenDistance, double screenWidth, double screenHeight)
    {
        if (isZero(screenDistance))
        {
            throw new IllegalArgumentException("distance cannot be 0");
        }
        Vector v=_vTo.scale(screenDistance);
        Point3D Pc = _p0.add(v);

        double Ry = screenHeight/nY;
        double Rx = screenWidth/nX;

        double yi =  ((i - nY/2d)*Ry + Ry/2d);
        double xj=   ((j - nX/2d)*Rx + Rx/2d);

        Point3D Pij = Pc;

        if (! isZero(xj))
        {
            Vector w=_vRight.scale(xj);
            Pij = Pij.add(w);
        }
        if (! isZero(yi))
        {
            Vector u=_vUp.scale(-yi);
            Pij = Pij.add(u);
        }

        Vector Vij = Pij.subtract(_p0);

        return new Ray(_p0,Vij);

    }

}
