package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;


import static primitives.Util.isZero;

/**
 * class Camera
 */
public class Camera {
    /**
     * fields for class camera
     */
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
        //all vectors need to be normalized
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
        if (isZero(screenDistance))//the distance between the camera and the view plane cannot be zero
        {
            throw new IllegalArgumentException("distance cannot be 0");
        }
        Vector v=_vTo.scale(screenDistance);//vto scaled by the screen distance
        Point3D Pc = _p0.add(v);//adds the new vector to the starting point of the ray

        double Ry = screenHeight/nY;//screen height divided by the view plane column pixels
        double Rx = screenWidth/nX;//screen width divided by the view plane row pixels

        double yi =  ((i - nY/2d)*Ry + Ry/2d);//(row pixels minus half of the view plane column pixels)*Ry +Ry/2
        double xj=   ((j - nX/2d)*Rx + Rx/2d);//(column pixels minus half of the view plane row pixels)*Rx +Rx/2

        Point3D Pij = Pc;//ray stating point + vup scaled by screen distance

        if (! isZero(xj))//we need to scale vright with it so it cannot be zero
        {
            Vector w=_vRight.scale(xj);
            Pij = Pij.add(w);
        }
        if (! isZero(yi))//we need to scale vup with it so it cannot be zero
        {
            Vector u=_vUp.scale(-yi);
            Pij = Pij.add(u);
        }

        Vector Vij = Pij.subtract(_p0);

        return new Ray(_p0,Vij);//returns a new ray with the same starting point and a different vector

    }


}
