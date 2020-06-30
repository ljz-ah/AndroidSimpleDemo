package ljz.app.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.hardware.biometrics.BiometricPrompt;
import android.os.CancellationSignal;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;

import androidx.core.view.ViewCompat;

import ljz.app.R;

import static android.view.View.MeasureSpec.AT_MOST;
import static android.view.View.MeasureSpec.EXACTLY;
import static android.view.View.MeasureSpec.UNSPECIFIED;

/**
 * @ClassName TouchSlidView
 * @Description TODO
 * @Author LJZ
 * @Date 2020/5/18 9:50
 * @Version 1.0
 */
@SuppressLint("AppCompatCustomView")
public class TouchSlidView extends View {
    private String TAG = "TouchSlidView";
    private Paint paint, paint2;
    private RectF drawRect;
    private int lastX, lastY;
    private Context context;
    private View parenView;
    private Scroller scroller;
    private int startX, startY;
    private int viewWidth;
    private LinearGradient mLinearGradient;
    private Matrix mGradientMatrix;
    private int mTransLate;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

//        int x = (int) event.getX();
//        int y = (int) event.getY();
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
//        Log.d(TAG, "On Touch ------>x " + x + "------y  " + y);
//        this.parenView = (View) this.getParent();
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                startX = x;
                startY = y;
                Log.d(TAG, "ACTION_DOWN------>");
//                int downX = (int) event.getX();
//                int downY = (int) event.getY();
//                Log.d(TAG, "ACTION_DOWN------>downX " + downX + "------downY  " + downY);
                break;
            case MotionEvent.ACTION_MOVE:
                int offSetX = x - lastX;
                int offSetY = y - lastY;
//                View parentView = ((View) (this.getParent()));
//                layout(getLeft() + offSetX, getTop() + offSetY, getRight() + offSetX, getBottom() + offSetY);
//                parenView.scrollBy(-offSetX, -offSetY);
//               parentView .scrollTo(parentView.getScrollX()+(-offSetX),parentView.getScrollY()+(-offSetY));


//                int offSetX = lastX-x;
//                int offSetY = lastY-y;
//                  offsetLeftAndRight(offSetX);
//                  offsetTopAndBottom(offSetY);
//                ConstraintLayout.LayoutParams layoutParams= (ConstraintLayout.LayoutParams) getLayoutParams();
//                LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) getLayoutParams();
//                layoutParams.leftMargin=getLeft()+offSetX;
//                layoutParams.topMargin=getTop()+offSetY;
//                layoutParams.rightMargin=getRight()+offSetX;
//                layoutParams.bottomMargin=getBottom()+offSetY;
//                setLayoutParams(layoutParams);
//                ViewGroup.MarginLayoutParams layoutParams1= (ViewGroup.MarginLayoutParams) getLayoutParams();
//                layoutParams1.leftMargin=getLeft()+offSetX;
//                layoutParams1.topMargin=getTop()+offSetY;
//                setLayoutParams(layoutParams1);


                /**
                 * 页面刷新方法 1. layout(int l,int r,int t,int b)
                 *             2.offsetLeftAndRight 刷新距离左边和右边的边距 offsetTopAndBottom 刷新距离顶部和底部的边距
                 *             3.LayoutParams 刷新
                 *             4.ViewGroupManagerLayoutParams 不需要考虑布局类型
                 *             5.scrollBy,scrollTo
                 *
                 */
                layout(getLeft()+offSetX,getTop()+offSetY,getRight()+offSetX,getBottom()+offSetY);
                lastX = x;
                lastY = y;

                Log.d(TAG, "ACTION_MOVE------>offSetX " + offSetX + "  offSetY--->" + offSetY);

                break;
            case MotionEvent.ACTION_BUTTON_PRESS:
                Log.d(TAG, "ACTION_BUTTON_PRESS------>");
                break;
            case MotionEvent.ACTION_BUTTON_RELEASE:
                Log.d(TAG, "ACTION_BUTTON_RELEASE------>");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "ACTION_CANCEL------>");
                break;
            case MotionEvent.ACTION_HOVER_ENTER:
                Log.d(TAG, "ACTION_HOVER_ENTER------>");
                break;
            case MotionEvent.ACTION_HOVER_EXIT:
                Log.d(TAG, "ACTION_HOVER_EXIT------>");
                break;
            case MotionEvent.ACTION_HOVER_MOVE:
                Log.d(TAG, "ACTION_HOVER_MOVE------>");
                break;
            case MotionEvent.ACTION_MASK:
                Log.d(TAG, "ACTION_MASK------>");
                break;
            case MotionEvent.ACTION_OUTSIDE:
                Log.d(TAG, "ACTION_OUTSIDE------>");
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                Log.d(TAG, "ACTION_POINTER_DOWN------>");
                break;
            case MotionEvent.ACTION_POINTER_INDEX_MASK:
                Log.d(TAG, "ACTION_POINTER_INDEX_MASK------>");
                break;
            case MotionEvent.ACTION_POINTER_INDEX_SHIFT:
                Log.d(TAG, "ACTION_POINTER_INDEX_SHIFT------>");
                break;
            case MotionEvent.ACTION_POINTER_UP:
                Log.d(TAG, "ACTION_POINTER_UP------>");
                break;
            case MotionEvent.ACTION_UP:
//                scroller.startScroll(parenView.getScrollX(), parenView.getScrollY(), -parenView.getScrollX(), -parenView.getScrollY(), 500);
//                invalidate();
                ViewCompat.postInvalidateOnAnimation(this);
                Log.d(TAG, "ACTION_UP------>");
                break;
            default:
                break;

        }


        return true;
//        return super.onTouchEvent(event);
    }

    public TouchSlidView(Context context) {
        super(context);
        initView(context, null);
    }

    public TouchSlidView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public TouchSlidView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    @SuppressLint("NewApi")
    private void initView(Context mContext, AttributeSet attrs) {
        this.context = mContext;

        scroller = new Scroller(mContext);
        if (null != attrs) {
            TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.TouchSlidView);
        }
        paint = new Paint();
        paint2 = new Paint();
        paint.setStyle(Paint.Style.FILL);

        paint.setTextSize(25);
        paint.setColor(context.getColor(R.color.colorPrimary));
        paint2.setColor(Color.YELLOW);

    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        Log.d(TAG, "computeScroll==");
        if (scroller.computeScrollOffset()) {

            parenView.scrollTo(scroller.getCurrX(), scroller.getFinalY());
//            invalidate();
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
//        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paint);
//        canvas.drawRect(10, 10, getMeasuredWidth() - 10, getMeasuredHeight() - 10, paint2);
//        canvas.save();
//        canvas.translate(30, 30);
        String s="Hello World! ";
        if(null!=mGradientMatrix){
            mTransLate+=viewWidth/5;
            //if()
        }





//        canvas.drawText(s, (getMeasuredWidth()-paint.measureText(s))/2, getMeasuredHeight()/2, paint);

        super.onDraw(canvas);
//        canvas.restore();
        Log.d(TAG, "onDraw==");
//        canvas.drawRect(drawRect, paint);
//        canvas.drawLine(0,height/2,width,height/2,paint);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d(TAG, "changed=== " + changed + " l=== " + l + " t=== " + t + " r====== " + r + " b==== " + b);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    if(viewWidth==0){
        viewWidth=getMeasuredWidth();
//        if(viewWidth>0){
//            paint=getPaint();
//            mLinearGradient=new LinearGradient(0,0,viewWidth,0,new int[]{Color.BLUE,Color.GREEN,Color.GRAY},null, Shader.TileMode.CLAMP);
//            mGradientMatrix=new Matrix();
//
//        }


    }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));

    }

    /**
     * 测量指定宽度
     *
     * @param width
     * @return
     */
    public int measureWidth(int width) {
        int specMode = MeasureSpec.getMode(width);
        int specSize = MeasureSpec.getSize(width);
        int result = 0;
        switch (specMode) {
            case EXACTLY:
                result = specSize;
                break;
            case AT_MOST:
                result = 200;
                result = Math.min(result, specSize);
                break;
            default:
                result = 200;
                break;
        }
        Log.d(TAG, "measureWidth  result ==>" + result);
        return result;


    }

    /**
     * 测量指定高度
     *
     * @param height
     * @return
     */
    public int measureHeight(int height) {
        int specMode = MeasureSpec.getMode(height);
        int specSize = MeasureSpec.getSize(height);
        int result = 0;
        switch (specMode) {
            case EXACTLY:
                result = specSize;
                break;
            case AT_MOST:
                result = 200;
                result = Math.min(result, specSize);
                break;
            default:
                result = 200;
                break;
        }
        Log.d(TAG, "measureHeight  result ==>" + result);
        return result;

    }
}
