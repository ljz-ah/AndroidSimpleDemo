//package ljz.app;
//
//import android.content.Context;
//import android.graphics.drawable.BitmapDrawable;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Handler;
//import android.support.annotation.Nullable;
//import android.support.annotation.RequiresApi;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.PopupWindow;
//
//import ljz.app.Interface.OnScreenClickListener;
//
//
///**
// * Created by <lzh> on 2017/8/3.
// */
//
//public abstract class BaseActivity extends AppCompatActivity {
//
//    private Context mContext;
//    private ScreenShotListenManager screenShotListenManager;
//    private boolean isHasScreenShotListener = false;
//    private String path;
//    private ImageView screenShotIv;
//    //    private LinearLayout rl_screen;
//    //    private ProgressBar progressBar;
//    private Handler mHandler = new Handler();
//    private Button btn_share, btn_report;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(setViewId());
//        screenShotListenManager = ScreenShotListenManager.newInstance(this);
//        mContext = this;
//    }
//
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        startScreenShotListen();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        stopScreenShotListen();
//
//    }
//
//    /**
//     * 监听
//     */
//    private void startScreenShotListen() {
//        if (!isHasScreenShotListener && screenShotListenManager != null) {
//            screenShotListenManager.setListener(new ScreenShotListenManager.OnScreenShotListener() {
//                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//                @Override
//                public void onShot(String imagePath) {
//
//                    ScreenDialogFragment screenDialogFragment = new ScreenDialogFragment();
//                    screenDialogFragment.setImagePath(imagePath);
//                    screenDialogFragment.show(BaseActivity.this.getSupportFragmentManager(), "");
//                    screenDialogFragment.setScreenClickListener(new OnScreenClickListener() {
//                        @Override
//                        public void shareClick() {
//
//                        }
//
//                        @Override
//                        public void reportClick() {
//
//                        }
//                    });
//
//
////                    DisplayMetrics metric = new DisplayMetrics();
////                    getWindowManager().getDefaultDisplay().getMetrics(metric);
////                    path = imagePath;
////                    LayoutInflater mLayoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
////                    ViewGroup mView = (ViewGroup) mLayoutInflater.inflate(
////                            R.layout.dialog_layout, null, true);
////                    btn_share = mView.findViewById(R.id.btn_share);
////                    btn_report = mView.findViewById(R.id.btn_report);
////                    Drawable drawabl = getResources().getDrawable(R.mipmap.icon_share);
////                    Drawable drawable_report = getResources().getDrawable(R.mipmap.icon_report);
////                    drawabl.setBounds(0, 0, getResources().getDimensionPixelOffset(R.dimen.pub_20dp), getResources().getDimensionPixelOffset(R.dimen.pub_20dp));
////
////                    btn_share.setCompoundDrawables(drawabl, null, null, null);
////
////                    drawable_report.setBounds(0, 0, getResources().getDimensionPixelOffset(R.dimen.pub_20dp), getResources().getDimensionPixelOffset(R.dimen.pub_20dp));
////
////                    btn_report.setCompoundDrawables(drawable_report, null, null, null);
////
////                    final PopupWindow popupWindow = new PopupWindow(mView,
////                            (int) getResources().getDimension(R.dimen.pub_100dp), (int) getResources().getDimension(R.dimen.pub_200dp), true);
////                    int popWidth = px2dip(BaseActivity.this, metric.widthPixels);
////                    int popHeight = px2dip(BaseActivity.this, metric.heightPixels);
////
////                    popupWindow.setBackgroundDrawable(null);
////                    View contentView = LayoutInflater.from(BaseActivity.this).inflate(R.layout.activity_main, null);
//
////                    popupWindow.getA
//
////                    popupWindow.setHeight((int) getResources().getDimension(R.dimen.pub_160dp));
////                    popupWindow.setWidth();
////                    popupWindow.update();
////                    rl_screen = mView.findViewById(R.id.rl_screen);
////                    rl_screen.getLayoutParams().height =
////                    rl_screen.getLayoutParams().width = (int) getResources().getDimension(R.dimen.pub_150dp);
////                    Log.e("ljz", "with " + rl_screen.getLayoutParams().width);
////                    Log.e("ljz", "popWidth " + getResources().getDimensionPixelOffset(R.dimen.pub_130dp));
////                    Log.e("ljz", "X location " + (getResources().getDisplayMetrics().widthPixels - contentView.getMeasuredWidth()));
////                    screenShotIv = mView.findViewById(R.id.iv_screen);
////                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) getResources().getDimension(R.dimen.pub_90dp), (int) getResources().getDimension(R.dimen.pub_120dp));
////                    layoutParams.gravity = Gravity.CENTER;
////                    layoutParams.setMargins(getResources().getDimensionPixelSize(R.dimen.pub_5dp), getResources().getDimensionPixelSize(R.dimen.pub_5dp), getResources().getDimensionPixelSize(R.dimen.pub_5dp), getResources().getDimensionPixelSize(R.dimen.pub_5dp));
////                    screenShotIv.setLayoutParams(layoutParams);
//
//                    Log.d("msg", "BaseActivity -> onShot: " + "获得截图路径：" + imagePath);
//
//
//                }
//            });
//            screenShotListenManager.startListen();
//            isHasScreenShotListener = true;
//        }
//    }
//
//    /**
//     * 停止监听
//     */
//    private void stopScreenShotListen() {
//        if (isHasScreenShotListener && screenShotListenManager != null) {
//            screenShotListenManager.stopListen();
//            isHasScreenShotListener = false;
//        }
//    }
//
//    protected abstract int setViewId();
//
//    //清空数据
//    private void ShowPopup() {
//        LayoutInflater mLayoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//        ViewGroup mView = (ViewGroup) mLayoutInflater.inflate(
//                R.layout.dialog_layout, null, true);
//        final PopupWindow popupWindow = new PopupWindow(mView,
//                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
//
//        popupWindow.setBackgroundDrawable(new BitmapDrawable());
//        popupWindow.showAtLocation(findViewById(R.id.activity_main),
//                Gravity.CENTER | Gravity.CENTER, 800, 0);//在屏幕的中间位置显示
//        popupWindow.update();
//    }
//
//    /**
//     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
//     */
//    public static int px2dip(Context context, float pxValue) {
//        final float scale = context.getResources().getDisplayMetrics().density;
//        return (int) (pxValue / scale + 0.5f);
//    }
//}
