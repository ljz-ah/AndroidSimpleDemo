package ljz.app.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ljz.app.R;

/**
 * @ClassName CusTextView
 * @Description TODO
 * @Author LJZ
 * @Date 2020/5/20 15:40
 * @Version 1.0
 */
@SuppressLint("AppCompatCustomView")
public class CusTextView extends TextView {
    public static final String TAG = "CusTextView";
    private int viewWidth;
    private LinearGradient mLinearGradient;
    private Matrix mGradientMatrix;
    private int mTransLate;
    private Paint paint, paint2;
    private float textWidth;

    public CusTextView(Context context) {
        super(context);
        initView(context);
    }

    public CusTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CusTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public CusTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }


    private void initView(Context context) {
        paint2 = new Paint();
        paint2.setTextSize(25);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setColor(context.getColor(R.color.colorPrimary));
        paint2.setStrokeWidth(5);
        textWidth=getPaint().measureText(getText().toString());
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
//        canvas.drawRect(viewWidth/2-(textWidth/2)-5, 0,(textWidth/2)+viewWidth/2+5 , getMeasuredHeight(), paint2);
//       Log.d("TAG"," paint.descent()==="+ paint.descent());
//        Log.d("TAG"," paint.ascent()==="+ paint.ascent());
        Log.d(TAG, "onDraw " + mTransLate);
//        if (null != mGradientMatrix) {
//            mTransLate += viewWidth / 8;
//            if (mTransLate > 2 * viewWidth) {
//                mTransLate = -viewWidth;
//            }
//            mGradientMatrix.setTranslate(mTransLate, 0);
//            mLinearGradient.setLocalMatrix(mGradientMatrix);
//            postInvalidateDelayed(50);
////          handler.sendEmptyMessage(0);
//
//        }

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d(TAG, "onSizeChanged==" + " w---" + w + " h---" + h + "  oldw---" + oldw + " oldh----" + oldh);
        drawLinearGradient();

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    paint.setShader(mLinearGradient);
                    break;

            }


        }
    };

    private void drawLinearGradient() {
        if (viewWidth == 0) {
            viewWidth = getMeasuredWidth();
            if (viewWidth > 0) {
                paint = getPaint();
                mLinearGradient = new LinearGradient(0, 0, viewWidth, 0, new int[]{Color.BLUE, Color.GREEN, Color.RED}, null, Shader.TileMode.CLAMP);
                paint.setShader(mLinearGradient);
                mGradientMatrix = new Matrix();
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.d(TAG, "changed=== " + changed + " l=== " + left + " t=== " + top + " r====== " + right + " b==== " + bottom);
//        super.onLayout(changed, left, top, right, bottom);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d(TAG, "onMeasure=== ");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
}
