package ljz.app.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @ClassName LayerView
 * @Description TODO
 * @Author LJZ
 * @Date 2020/6/11 13:47
 * @Version 1.0
 */
public class LayerView extends View {
    private Paint paint1, paint2;

    public LayerView(Context context) {
        super(context);
        initPaint();
    }

    public LayerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public LayerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    public LayerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaint();
    }

    private void initPaint() {
        paint1 = new Paint();
        paint2 = new Paint();
        paint1.setColor(Color.RED);
        paint2.setColor(Color.YELLOW);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        ColorMatrix colorMatrix=new ColorMatrix();
        colorMatrix.setRotate(0,180);
        colorMatrix.setSaturation(1);
        colorMatrix.setScale(255,0,0,1);
//        colorMatrix.setRotate(1,60);
//        colorMatrix.setRotate(2,60);
        ColorFilter colorFilter=new ColorMatrixColorFilter(colorMatrix);
        paint1.setColorFilter(colorFilter);
        canvas.drawCircle(150, 150, 100, paint1);
//        canvas.saveLayerAlpha(0, 0, 400, 400, 0);
//        canvas.drawCircle(200, 200, 100, paint2);


//        ColorMatrix colorMatrix1=new ColorMatrix();
        //设置颜色的饱和度
//        colorMatrix1.setSaturation(0);
//        colorMatrix1.setScale();

    }
}
