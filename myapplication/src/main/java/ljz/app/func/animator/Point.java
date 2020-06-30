package ljz.app.func.animator;

/**
 * 项目名称：LJZ_DEMO
 *
 * @author LJZ
 * Created Time  2019/10/29 16:51.
 * @version 1.0
 */
public class Point {
    public Point(float x,float y){
        this.X=x;
        this.Y=y;
    }


    private float X;

    public float getX() {
        return X;
    }

    public void setX(float x) {
        X = x;
    }

    public float getY() {
        return Y;
    }

    public void setY(float y) {
        Y = y;
    }

    private float Y;
}
