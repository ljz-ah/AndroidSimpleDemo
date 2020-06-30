package ljz.app.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import ljz.app.R;

/**
 * @ClassName ArcView
 * @Description TODO
 * @Author LJZ
 * @Date 2020/5/27 9:32
 * @Version 1.0
 */
public class CircleProgressView extends View {
    private float mCircleX, mRadius, mCircleY;
    private Paint circlePaint, arcPaint, outArcPaint, textPaint, pointPaint;
    private RectF mRectF;
    private float curWidth;
    private String progressValue = "10%";
    private float mSweepValue = 10;


    public CircleProgressView(Context context) {
        super(context);
        initPaint();
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaint();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        initPaint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        curWidth = w;
        mCircleX = w / 2;
        mCircleY = h / 2;
        mRadius = (float) (mCircleX * 0.5 / 2);


    }

    /**
     *
     */
    private void initPaint() {

        circlePaint = new Paint();
        circlePaint.setColor(Color.LTGRAY);
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setStrokeWidth(10);

        arcPaint = new Paint();
        arcPaint.setColor(Color.GREEN);
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setStrokeWidth(10);

        outArcPaint = new Paint();
        outArcPaint.setStrokeWidth(13);
        outArcPaint.setStyle(Paint.Style.STROKE);
        outArcPaint.setColor(getResources().getColor(R.color.FF9800));

        textPaint = new Paint();
        textPaint.setStrokeWidth(2);
        textPaint.setTextSize(30);
        textPaint.setTextAlign(Paint.Align.CENTER);

        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.RED);

        pointPaint = new Paint();
        pointPaint.setColor(Color.BLUE);
        pointPaint.setStrokeWidth(15);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mCircleX, mCircleY, mRadius, circlePaint);
//        canvas.drawPoint(mCircleX,mCircleY,pointPaint);


        mRectF = new RectF((float) (mCircleX - mRadius * 1.3), (float) (mCircleY - mRadius * 1.3),
                (float) (mCircleX + mRadius * 1.3), (float) (mCircleY + mRadius * 1.3));

//        canvas.drawRect(mRectF, arcPaint);
        canvas.drawArc(mRectF, 270, 360, false, arcPaint);
        canvas.drawArc(mRectF, 270, mSweepValue, false, outArcPaint);

        float textWidth = textPaint.measureText(progressValue);

        canvas.drawText(progressValue, 0, progressValue.length(), mCircleX, mCircleY + (pointPaint.getTextSize() / 2), textPaint);

    }

    public void setSweepValue(float sweepValue) {
        if (sweepValue != 0) {
            float realSweep = 360 * (sweepValue / 100);
            mSweepValue = realSweep;
        } else {
            mSweepValue = 25;
        }
        postInvalidate();
    }
}
