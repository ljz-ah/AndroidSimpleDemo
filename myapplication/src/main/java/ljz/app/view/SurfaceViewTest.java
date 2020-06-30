package ljz.app.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * @ClassName SurfaceViewTest
 * @Description TODO
 * @Author LJZ
 * @Date 2020/6/12 14:54
 * @Version 1.0
 */
public class SurfaceViewTest extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    private boolean isDrawing;
    int x, y;
    private Path mPath, cosPath;
    private Paint paint, cosPaint;

    public SurfaceViewTest(Context context) {
        super(context);
        initView();
    }

    public SurfaceViewTest(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SurfaceViewTest(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public SurfaceViewTest(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isDrawing = true;
        Log.d("TAG","surfaceCreated-->");
        new Thread(this).start();
//        mCanvas= mHolder.lockCanvas();
//        mCanvas.drawColor(Color.WHITE);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {



        int   startX = (int) event.getX();
        int startY = (int) event.getY();

        Log.d("TAG", "startX--->" + startX + "   startY------->" + startY);
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(startX,startY);

                break;
            case MotionEvent.ACTION_MOVE:

                mPath.lineTo(startX, startY);

                break;
            case MotionEvent.ACTION_UP:



                break;
        }


        return true;
//        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isDrawing = false;
    }

    private void initView() {
        mHolder = getHolder();
        mHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setKeepScreenOn(true);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        paint.setAntiAlias(true);
        cosPath = new Path();
        mPath = new Path();
        cosPaint = new Paint();
        cosPaint.setColor(Color.YELLOW);
        cosPaint.setStyle(Paint.Style.STROKE);
        cosPaint.setStrokeWidth(10);

    }


    @Override
    public void run() {
        long startTime=System.currentTimeMillis();
        while (isDrawing) {
            draw();
//            x += 1;
//            y = (int) (100*Math.sin(x * 2 * Math.PI / 180) + 100);


//            Log.d("TAG", "X--->" + x + "   Y------->" + y);
//            cosY= (int) ( 100*(Math.cos(x*2*Math.PI/180)+400));
//            mPath.lineTo(x, y);


        }
        long endTime=System.currentTimeMillis();
        if(endTime-startTime<100){
            try {
                Thread.sleep(100-(endTime-startTime));
            }catch (Exception e){

            }
        }


    }


    private void draw() {
        try {
            mCanvas = mHolder.lockCanvas();
            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawPath(mPath, paint);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (null != mCanvas) {
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }


    }
}
