package ljz.app.func.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;

import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import org.jetbrains.annotations.Nullable;

import java.util.Random;

/**
 * 项目名称：LJZ_DEMO
 *
 * @author LJZ ljz@yitong.com.cn
 * Created Time  2019/9/26 13:44.
 * @version 1.0
 */
public class MyView extends View {
    Paint paint;
    Paint paint2;
    int mViewWidth = 0;
    private Bitmap bitmap;
    private LinearGradient mLinearGradient;
    private Matrix mGradientMatrix;
    private Paint mPaint;
    private int mTranslate = 0;
    private int colors[] = {Color.BLUE, Color.RED, Color.GREEN};
    private boolean isRuning = false;

    public MyView(Context context) {
        super(context);
        initView();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    private void initView() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);


        paint2 = new Paint();
        paint2.setColor(Color.YELLOW);
        paint2.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e("onSizeChanged","w "+w+" h "+h+" oldw "+oldw+" oldh "+oldh);
//        if (mViewWidth == 0) {
//            mViewWidth = getMeasuredWidth();
//            if (mViewWidth > 0) {
//                mPaint = getPaint();
//                mLinearGradient = new LinearGradient(0, 0, mViewWidth, 0, new int[]{Color.BLUE, 0xfffff, Color.BLUE}, null, Shader.TileMode.CLAMP);
//                if (null != mPaint) {
//                    mPaint.setShader(mLinearGradient);
//                    mGradientMatrix = new Matrix();
//                }
//
//            }


//        }


    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        canvas.drawText("HelloWorld", 10, 20, paint);
        Log.e("TAG", "onDraw " + isRuning);
//        if (!isRuning) {
//        handler.sendEmptyMessageDelayed(0,800);
//        }
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                Random random = new Random();
                int color = random.nextInt(colors.length);
                int colorValue = colors[color];
                paint.setColor(colorValue);
                isRuning = true;
                invalidate();
            }


        }
    };


    private int measureWidth(int width) {
        int sepcMode = MeasureSpec.getMode(width);
        int sepcSize = MeasureSpec.getSize(width);
        int result = 0;
        if (sepcMode == MeasureSpec.EXACTLY) {
            result = sepcSize;
        } else {
            result = 200;

            if (sepcMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, sepcSize);

            }

        }

        return result;
    }

    private int measureHeight(int height) {
        int sepcMode = MeasureSpec.getMode(height);
        int sepcSize = MeasureSpec.getSize(height);
        int result = 0;
        if (sepcMode == MeasureSpec.EXACTLY) {
            result = sepcSize;
        } else {
            result = 200;
            if (sepcMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, sepcSize);
            }

        }

        return result;
    }

}
