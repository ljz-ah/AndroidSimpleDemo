package ljz.app.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.logging.Handler;

/**
 * @ClassName TimeView
 * @Description TODO
 * @Author LJZ
 * @Date 2020/6/1 13:02
 * @Version 1.0
 */
public class TimeClockView extends View {
    private int width, height;
    private Paint clockPaint, clockTextPaint, clockHourPaint,clockMinutePaint,clockLinePaint, centerPaint;

    public TimeClockView(Context context) {
        super(context);
        initPaint();
    }

    public TimeClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public TimeClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    public TimeClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }

    private void initPaint() {
        clockPaint = new Paint();
        clockPaint.setAntiAlias(true);
        clockPaint.setColor(Color.BLACK);
        clockPaint.setStrokeWidth(10);
        clockPaint.setStyle(Paint.Style.STROKE);

        clockLinePaint = new Paint();
        clockLinePaint.setAntiAlias(true);
        clockLinePaint.setColor(Color.BLACK);
        clockLinePaint.setStrokeWidth(5);

        centerPaint = new Paint();
        centerPaint.setStrokeWidth(10);
        centerPaint.setStyle(Paint.Style.FILL);
        centerPaint.setColor(Color.RED);

        clockTextPaint=new Paint();
        clockTextPaint.setTextSize(20);
        clockTextPaint.setStrokeWidth(15);
        clockTextPaint.setColor(Color.RED);

        clockHourPaint=new Paint();
        clockHourPaint.setStrokeWidth(20);

        clockMinutePaint=new Paint();
        clockMinutePaint.setStrokeWidth(10);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(width / 2, height / 2, width / 3, clockPaint);
        for (int i = 0; i < 12 * 5; i++) {
            if (i % 5 == 0) {
                canvas.drawLine(width / 2, height / 2 - (width / 3), width / 2, height / 2 - (width / 3) + 50, clockLinePaint);
         String clockText=String.valueOf(i/5);

          float textWidth=clockTextPaint.measureText(clockText);
           canvas.drawText(clockText,width / 2-textWidth/2,height / 2 - (width / 3)+70,clockTextPaint);
            } else {
                canvas.drawLine(width / 2, height / 2 - (width / 3), width / 2, height / 2 - (width / 3) + 20, clockLinePaint);
            }
            canvas.rotate(6, width / 2, height / 2);
        }
        canvas.save();
        canvas.translate(width/2,height/2);
//        canvas.drawLine(0,0,100,100,clockHourPaint);

        canvas.drawLine(0,0,0,-(width/3),clockMinutePaint);

        canvas.drawPoint(width / 2, height / 2, centerPaint);


    }


//    /**
//     * 根据角度和长度计算线段的起点和终点的坐标
//     * @param angle
//     * @param length
//     * @return
//     */
//    private float[] calculatePoint(float angle, float length){
//        float[] points = new float[4];
//        if(angle <= 90f){
//            points[0] = -(float) Math.sin(angle*Math.PI/180) * DEFAULT_POINT_BACK_LENGTH;
//            points[1] = (float) Math.cos(angle*Math.PI/180) * DEFAULT_POINT_BACK_LENGTH;
//            points[2] = (float) Math.sin(angle*Math.PI/180) * length;
//            points[3] = -(float) Math.cos(angle*Math.PI/180) * length;
//        }else if(angle <= 180f){
//            points[0] = -(float) Math.cos((angle-90)*Math.PI/180) * DEFAULT_POINT_BACK_LENGTH;
//            points[1] = -(float) Math.sin((angle-90)*Math.PI/180) * DEFAULT_POINT_BACK_LENGTH;
//            points[2] = (float) Math.cos((angle-90)*Math.PI/180) * length;
//            points[3] = (float) Math.sin((angle-90)*Math.PI/180) * length;
//        }else if(angle <= 270f){
//            points[0] = (float) Math.sin((angle-180)*Math.PI/180) * DEFAULT_POINT_BACK_LENGTH;
//            points[1] = -(float) Math.cos((angle-180)*Math.PI/180) * DEFAULT_POINT_BACK_LENGTH;
//            points[2] = -(float) Math.sin((angle-180)*Math.PI/180) * length;
//            points[3] = (float) Math.cos((angle-180)*Math.PI/180) * length;
//        }else if(angle <= 360f){
//            points[0] = (float) Math.cos((angle-270)*Math.PI/180) * DEFAULT_POINT_BACK_LENGTH;
//            points[1] = (float) Math.sin((angle-270)*Math.PI/180) * DEFAULT_POINT_BACK_LENGTH;
//            points[2] = -(float) Math.cos((angle-270)*Math.PI/180) * length;
//            points[3] = -(float) Math.sin((angle-270)*Math.PI/180) * length;
//        }
//        return points;
//    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));

    }


    private int measureWidth(int width) {
        int sizeMode = MeasureSpec.getMode(width);
        int size = MeasureSpec.getSize(width);
        int defaultSize = 0;
        switch (sizeMode) {
            case MeasureSpec.AT_MOST:
                defaultSize = 200;
                defaultSize = Math.min(defaultSize, size);

                break;
            case MeasureSpec.EXACTLY:
                defaultSize = size;
                break;
            default:
                defaultSize = 200;
                break;
        }

        return defaultSize;
    }

    private int measureHeight(int height) {
        int sizeMode = MeasureSpec.getMode(height);
        int size = MeasureSpec.getSize(height);
        int defaultSize = 0;
        switch (sizeMode) {
            case MeasureSpec.AT_MOST:
                defaultSize = 100;
                defaultSize = Math.min(defaultSize, size);

                break;
            case MeasureSpec.EXACTLY:
                defaultSize = size;

                break;
            default:
                defaultSize = 100;
                break;
        }

        return defaultSize;
    }
}
