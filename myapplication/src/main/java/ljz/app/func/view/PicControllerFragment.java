package ljz.app.func.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import ljz.app.R;

/**
 * @ClassName PicControllerFragment
 * @Description TODO
 * @Author LJZ
 * @Date 2020/6/12 14:11
 * @Version 1.0
 */
public class PicControllerFragment extends DialogFragment implements SeekBar.OnSeekBarChangeListener {
    ImageView iv_photo;
    float mHue = 0.0f;
    float mSaturation = 1f;
    float mLum = 1f;
    float MID_VALUE;
    Bitmap oriBitmap,newBitmap;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_pic_cintro, container);
        initView(view);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.Fragment_Dialog_full);
    }


    private void initView(View view){
        iv_photo = (ImageView) view.findViewById(R.id.iv_photo);
        SeekBar barHue = (SeekBar) view.findViewById(R.id.seekbarHue);
        SeekBar barSaturation = (SeekBar) view.findViewById(R.id.seekbarSaturation);
        SeekBar barLum = (SeekBar) view.findViewById(R.id.seekbarLum);
        MID_VALUE = barHue.getMax() * 1.0F / 2;
        oriBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.pic);
        //Android系统不允许直接修改原图
        newBitmap = Bitmap.createBitmap(oriBitmap.getWidth(), oriBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        barHue.setOnSeekBarChangeListener(this);
        barSaturation.setOnSeekBarChangeListener(this);
        barLum.setOnSeekBarChangeListener(this);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.seekbarHue:
                mHue = (progress - MID_VALUE) * 1.0F / MID_VALUE * 180;
                break;
            case R.id.seekbarSaturation:
                mSaturation = progress * 1.0F / MID_VALUE;
                break;
            case R.id.seekbarLum:
                mLum = progress * 1.0F / MID_VALUE;
                break;
        }
        iv_photo.setImageBitmap(handleImageEffect(oriBitmap,newBitmap, mHue, mSaturation, mLum));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public static Bitmap handleImageEffect(Bitmap oriBmp, Bitmap bmp, float hue, float saturation, float lum) {
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();

        ColorMatrix hueMatrix = new ColorMatrix();
        hueMatrix.setRotate(0, hue);
        hueMatrix.setRotate(1, hue);
        hueMatrix.setRotate(2, hue);

        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(saturation);

        ColorMatrix lumMatrix = new ColorMatrix();
        lumMatrix.setScale(lum, lum, lum, 1);

        ColorMatrix imageMatrix = new ColorMatrix();
        imageMatrix.postConcat(hueMatrix);
        imageMatrix.postConcat(saturationMatrix);
        imageMatrix.postConcat(lumMatrix);

        paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));
        canvas.drawBitmap(oriBmp, 0, 0, paint);

        return bmp;
    }
}
