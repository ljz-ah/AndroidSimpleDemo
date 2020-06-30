package ljz.app.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @ClassName VoiceMapView
 * @Description TODO
 * @Author LJZ
 * @Date 2020/5/27 14:12
 * @Version 1.0
 */
public class MusicMapView extends View {
    private Paint rectPaint;
    private float rectWidth, rectHeight, mWidth;
    private int singleRectWidth=35;
    private LinearGradient mLinearGradient;
    private int rectCount;

    public MusicMapView(Context context) {
        super(context);
        initPaint();
    }

    public MusicMapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public MusicMapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    public MusicMapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawReactVoice(canvas);
        canvas.save();
//        postInvalidateDelayed(200);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void initPaint() {
        rectPaint = new Paint();
        rectPaint.setStyle(Paint.Style.FILL);
//        rectPaint.setStrokeWidth(10);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getWidth();
        rectHeight = getHeight();

        rectCount= (int) (mWidth/singleRectWidth);

        mLinearGradient = new LinearGradient(0, 0, mWidth, rectHeight, new int[]{Color.BLUE,Color.YELLOW,Color.CYAN,Color.GREEN}, new float[]{0.3f,0.5f,0.7f,0.9f}, Shader.TileMode.CLAMP);
        rectPaint.setShader(mLinearGradient);
    }

    /**
     *
     */
    private void drawReactVoice(Canvas canvas) {
        for (int i = 0; i < rectCount; i++) {
//        canvas.drawRect(0f,(float) (rectHeight-randomH()),rectWidth,rectHeight,rectPaint);
            canvas.drawRect((float) (singleRectWidth+singleRectWidth*i),(float) (rectHeight-randomH()),
                    (float) (singleRectWidth+singleRectWidth*i+20),rectHeight,rectPaint);

        }
    }

    //随机生成矩形的高度，形成梯度
    private int randomH(){
        double rand = Math.random();
        return (int)(rand* rectHeight);
    }
}
