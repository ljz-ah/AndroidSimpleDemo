package ljz.app.view.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Scroller;

import ljz.app.utils.DisplayUtil;

/**
 * @ClassName ScrollViewGroup
 * @Description TODO
 * @Author LJZ
 * @Date 2020/5/27 15:15
 * @Version 1.0
 */
public class ScrollViewGroup extends ViewGroup {
    private int mScreenHeight;
    private int lastY, mStart, mEnd;
    private Scroller mScroller;

    public ScrollViewGroup(Context context) {
        super(context);
        init(context);
    }

    public ScrollViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ScrollViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public ScrollViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }


    private void init(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            DisplayMetrics outMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getRealMetrics(outMetrics);
            mScreenHeight = outMetrics.heightPixels;
        }

        mScroller = new Scroller(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();

        //childLeft和childTop代表在StaggerLayout的坐标系中，能够用来layout子View的区域的
        //左上角的顶点的坐标。
//        final int childLeft = getPaddingLeft();
//        final int childTop = getPaddingTop();
//
//        //childRight代表在StaggerLayout的坐标系中，能够用来layout子View的区域的
//        //右边那条边的坐标。
//        final int childRight = r - l - getPaddingRight();
//
//
//        /*
//          curLeft和curTop代表StaggerLayout准备用来layout子View的起点坐标，这个点的坐标随着
//          子View一个一个地被layout，在不断变化，有点像数据库中的Cursor，指向下一个可用区域。
//          maxHeight代表当前行中最高的子View的高度，当需要换行时，curTop要加上该值，以确保新行中
//          的子View不会与上一行中的子View发生重叠。
//         */
//        int curLeft, curTop, maxHeight;
//
//        maxHeight = 0;
//        curLeft = childLeft;
//        curTop = childTop;
//
//        for (int i = 0; i < count; i++) {
//            View child = getChildAt(i);
//
//            if (child.getVisibility() == GONE)
//                continue;
//
//            int curWidth, curHeight;
//            curWidth = child.getMeasuredWidth();
//            curHeight = child.getMeasuredHeight();
//            //用来判断是否应当将该子View放到下一行
//            if (curLeft + curWidth >= childRight) {
//                    /*
//                    需要移到下一行时，更新curLeft和curTop的值，使它们指向下一行的起点
//                    同时将maxHeight清零。
//                     */
//                curLeft = childLeft;
//                curTop += maxHeight;
//                maxHeight = 0;
//            }
//            //所有的努力只为了这一次layout
//            child.layout(curLeft, curTop, curLeft + curWidth, curTop + curHeight);
//            //更新maxHeight和curLeft
//            if (maxHeight < curHeight)
//                maxHeight = curHeight;
//            curLeft += curWidth;
//        }


        int childCount = getChildCount();
        Log.d("TAG", "childCount==" + childCount);
        MarginLayoutParams layoutParams = (MarginLayoutParams) getLayoutParams();
        layoutParams.height = mScreenHeight* childCount;
        setLayoutParams(layoutParams);
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            if (childView.getVisibility() != GONE) {
                childView.layout(l,mScreenHeight*i, r, mScreenHeight*(i+1));
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getY();
        Log.d("TAG", "y==" + y);
        Log.d("TAG", "getScrollY==" + getScrollY());
        LayoutParams layoutParams = getLayoutParams();

        Log.d("TAG", "layoutParams.height==" + layoutParams.height);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastY = y;
                mStart = getScrollY();
                Log.d("TAG", "mStart==" + mStart);
                break;
            case MotionEvent.ACTION_MOVE:

                Log.d("TAG", "getHeight==" + getHeight());
                Log.d("TAG", "mScreenHeight==" + mScreenHeight);

                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                int distanceY = lastY - y;
//                if(getScrollY()>=layoutParams.height){
//                    distanceY=layoutParams.height;
//                    return false;
//                }
//                if (getScrollY() >= layoutParams.height) {
//
//
//                }
                if (getScrollY() < 0) {
                    distanceY = 0;
                }


                Log.d("TAG", "distanceY==" + distanceY);

                scrollBy(0, distanceY);
                lastY = y;
                Log.d("TAG", "lastY==" + y);
                break;
            case MotionEvent.ACTION_UP:

                mEnd = getScrollY();

                Log.d("TAG", "mEnd==" + mEnd);
                int dScrollY = mEnd - mStart;
                Log.d("TAG", "dScrollY==" + dScrollY);
                Log.d("TAG", "mScreenHeight/3==" + mScreenHeight / 3);
                if (mEnd + mScreenHeight >= layoutParams.height) {
                    dScrollY = mEnd - mScreenHeight;
                }
                if (dScrollY > 0) {
                    if (dScrollY < mScreenHeight / 3) {
                        mScroller.startScroll(
                                0, getScrollY(),
                                0, -dScrollY);
                    } else {

                        mScroller.startScroll(
                                0, getScrollY(),
                                0, mScreenHeight - dScrollY);
                    }


                } else {
                    if (-dScrollY < mScreenHeight / 3) {
                        mScroller.startScroll(
                                0, getScrollY(),
                                0, -dScrollY);
                    } else {
                        mScroller.startScroll(
                                0, getScrollY(),
                                0, -mScreenHeight - dScrollY);
                    }
                }


                break;


        }
        postInvalidateOnAnimation();
//        return super.onTouchEvent(event);

        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        Log.d("TAG", "computeScroll--->");
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
            postInvalidateOnAnimation();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);

        }

    }
}
