package ljz.app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @ClassName WorkView
 * @Description TODO
 * @Author LJZ
 * @Date 2020/5/28 15:07
 * @Version 1.0
 */
public class WorkView extends View {
    public WorkView(Context context) {
        super(context);
    }

    public WorkView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WorkView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public WorkView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("TAG",getClass().getSimpleName()+" onTouchEvent  "+event.getAction());
        return super.onTouchEvent(event);
    }
}
