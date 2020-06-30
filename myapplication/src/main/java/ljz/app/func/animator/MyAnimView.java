package ljz.app.func.animator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 项目名称：LJZ_DEMO
 *
 * @author LJZ ljz@yitong.com.cn
 * Created Time  2019/10/29 16:50.
 * @version 1.0
 */
public class MyAnimView extends View {

    private final float INIT_VALUE = 50f;
    Point curPoint;
    Paint paint;


    public MyAnimView(Context context) {
        super(context);
    }

    public MyAnimView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyAnimView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView(){
        paint=new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.RED);

        if(curPoint==null){
            curPoint=new Point(INIT_VALUE,INIT_VALUE);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
