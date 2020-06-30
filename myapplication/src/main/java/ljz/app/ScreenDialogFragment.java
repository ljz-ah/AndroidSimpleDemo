package ljz.app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import ljz.app.Interface.OnScreenClickListener;

/**
 * 项目名称：LJZ_DEMO
 *
 * @author LJZ ljz@yitong.com.cn
 * Created Time  2019/7/11 23:02.
 * @version 1.0
 */
public class ScreenDialogFragment extends DialogFragment {
    private Button btn_share, btn_report;
    private Handler mHandler = new Handler();
    private ImageView screenShotIv;
    private String shotImagePath = "";
    Runnable loadImage;

    public void setScreenClickListener(OnScreenClickListener screenClickListener) {
        this.screenClickListener = screenClickListener;
    }

    private OnScreenClickListener screenClickListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//        if(null!=savedInstanceState){
//            shotImagePath=savedInstanceState.getString("ImagePath");
//            if(!"".equals(shotImagePath)){
//                setImagePath(shotImagePath);
//            }
//        }

        View mView = inflater.inflate(
                R.layout.dialog_layout, container, false);
        // 设置背景透明
        getDialog().getWindow().setBackgroundDrawableResource(R.color.transparent);
        //去掉标题
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);


        return mView;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        screenShotIv = view.findViewById(R.id.iv_screen);
        btn_share = view.findViewById(R.id.btn_share);
        btn_report = view.findViewById(R.id.btn_report);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) getResources().getDimension(R.dimen.pub_90dp), (int) getResources().getDimension(R.dimen.pub_120dp));
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.setMargins(getResources().getDimensionPixelSize(R.dimen.pub_5dp), getResources().getDimensionPixelSize(R.dimen.pub_5dp), getResources().getDimensionPixelSize(R.dimen.pub_5dp), getResources().getDimensionPixelSize(R.dimen.pub_5dp));
        screenShotIv.setLayoutParams(layoutParams);
        Drawable drawabl = getActivity().getResources().getDrawable(R.mipmap.icon_share);
        Drawable drawable_report = getActivity().getResources().getDrawable(R.mipmap.icon_report);
        drawabl.setBounds(0, 0, getActivity().getResources().getDimensionPixelOffset(R.dimen.pub_20dp), getActivity().getResources().getDimensionPixelOffset(R.dimen.pub_20dp));
        btn_share.setCompoundDrawables(drawabl, null, null, null);
        drawable_report.setBounds(0, 0, getActivity().getResources().getDimensionPixelOffset(R.dimen.pub_20dp), getActivity().getResources().getDimensionPixelOffset(R.dimen.pub_20dp));
        btn_report.setCompoundDrawables(drawable_report, null, null, null);
        initAction();
    }

    private void initAction() {
        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null!=screenClickListener){
                    screenClickListener.shareClick();
                }

            }
        });

        btn_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null!=screenClickListener){
                    screenClickListener.reportClick();
                }
            }
        });

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Window window = getDialog().getWindow();
        getDialog().setCanceledOnTouchOutside(true);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        window.setWindowAnimations(R.style.dialogWindowAnim);
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.dimAmount = 0f;
        wlp.x = (int) getResources().getDimension(R.dimen.pub_10dp);

        window.setGravity(Gravity.CENTER_VERTICAL | Gravity.END);
        wlp.width = (int) getResources().getDimension(R.dimen.pub_100dp);
        wlp.height = (int) getResources().getDimension(R.dimen.pub_200dp);
        window.setAttributes(wlp);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putString("ImagePath", shotImagePath);

    }

    public void setImagePath(final String imagePath) {
        this.shotImagePath = imagePath;
        loadImage = new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                screenShotIv.setImageBitmap(bitmap);
            }
        };
        mHandler.postDelayed(loadImage, 1000);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mHandler.removeCallbacks(loadImage);
        Log.e("TAG", "onDetach ");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("TAG", "onDestroy ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("TAG", "onDestroyView ");
    }
}
