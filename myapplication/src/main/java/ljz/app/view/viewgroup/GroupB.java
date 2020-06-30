package ljz.app.view.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * @ClassName GroupB
 * @Description TODO
 * @Author LJZ
 * @Date 2020/5/28 15:02
 * @Version 1.0
 */
public class GroupB extends ViewGroup {
    public GroupB(Context context) {
        super(context);
    }

    public GroupB(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GroupB(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GroupB(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int curWidth, curHeight, childTop, childLeft = 0, childRight;
        int nextHeight=0;
        Log.d("TAG", "left--" + l + " top---" + t + " r---- " + r + " b---" + b);
        Log.d("TAG", "getPaddingLeft" + this.getPaddingLeft());
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            if (childView.getVisibility() == VISIBLE) {

                curWidth = childView.getMeasuredWidth();
                curHeight = childView.getMeasuredHeight();
                childTop = childView.getPaddingTop();
                childRight = curWidth + childView.getPaddingLeft();


                Log.d("TAG", "curWidth" + curWidth + " curHeight " + curHeight);
                Log.d("TAG", "childTop" + childView.getPaddingLeft());
               childView.layout(childLeft, nextHeight, childRight, nextHeight+curHeight);
                nextHeight+=curHeight;
            }


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

//    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        //childLeft和childTop代表在StaggerLayout的坐标系中，能够用来layout子View的区域的
//        //左上角的顶点的坐标。
//        final int childLeft = getPaddingLeft();
//        final int childTop = getPaddingTop();
//        final int count = getChildCount();
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
//
//    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("TAG", getClass().getSimpleName() + " dispatchTouchEvent  " + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("TAG", getClass().getSimpleName() + " onInterceptTouchEvent  " + ev.getAction());
//        return true;
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("TAG", getClass().getSimpleName() + " onTouchEvent  " + event.getAction());
//        return super.onTouchEvent(event);
        return true;
    }

}
