package ljz.app.view.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.customview.widget.ViewDragHelper;

import static androidx.customview.widget.ViewDragHelper.*;

/**
 * @ClassName GroupA
 * @Description TODO
 * @Author LJZ
 * @Date 2020/5/28 15:01
 * @Version 1.0
 */
public class GroupA extends FrameLayout {
    private ViewDragHelper viewDragHelper;
    private View menView, mainView;


    public GroupA(Context context) {
        super(context);
        initDragHelper();
    }

    public GroupA(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDragHelper();
    }

    public GroupA(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDragHelper();
    }

    public GroupA(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initDragHelper();
    }

    private void initDragHelper() {
        viewDragHelper = create(this, callback);


    }

    private ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {


        //何时检测触摸事件
        @Override
        public boolean tryCaptureView(@NonNull View child, int pointerId) {
            //当触摸的是菜单
            return child == mainView;
        }


        //垂直滑动
        @Override
        public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
            return 0;
        }

        //横向滑动
        @Override
        public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
            return left;
        }

        //拖动结束后
        @Override
        public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            if(mainView.getLeft()<500){
                viewDragHelper.smoothSlideViewTo(mainView,0,0);
                ViewCompat.postInvalidateOnAnimation(GroupA.this);
            }else {
                viewDragHelper.smoothSlideViewTo(mainView,menuViewWidth,0);
                ViewCompat.postInvalidateOnAnimation(GroupA.this);
            }
        }
    };


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("TAG", getClass().getSimpleName() + "  onTouchEvent  " + event.getAction());
        viewDragHelper.processTouchEvent(event);
        return true;
//        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("TAG", getClass().getSimpleName() + "  dispatchTouchEvent  " + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("TAG", getClass().getSimpleName() + "  onInterceptTouchEvent  " + ev.getAction());
//        return super.onInterceptTouchEvent(ev);
        return viewDragHelper.shouldInterceptTouchEvent(ev);
    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        if (viewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }

    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        menView = getChildAt(0);
        mainView = getChildAt(1);

    }

    int menuViewWidth;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        menuViewWidth = menView.getMeasuredWidth();
    }
}
