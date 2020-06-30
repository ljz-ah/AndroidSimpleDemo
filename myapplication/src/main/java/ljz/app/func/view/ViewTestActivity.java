package ljz.app.func.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;


import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import org.jetbrains.annotations.Nullable;

import ljz.app.R;
import ljz.app.func.surfaceview.SurfaceViewActivity;
import ljz.app.view.CircleProgressView;
import ljz.app.view.CusListView;
import ljz.app.view.TitleBarView;

/**
 * 项目名称：LJZ_DEMO
 *
 * @author LJZ
 * Created Time  2019/9/26 13:51.
 * @version 1.0
 */
public class ViewTestActivity extends FragmentActivity {
    private TitleBarView barView;
    private CircleProgressView circleView;
    //    private CusListView lv_test;
    private ArrayAdapter lvAdapter;
    String[] programs = {"JAVA", "C", "C++", "Python", "KOTLIN", "C", "C++", "Python", "KOTLIN", "C", "C++", "Python", "KOTLIN", "C", "C++", "Python", "KOTLIN", "C", "C++", "Python", "KOTLIN", "C", "C++", "Python", "KOTLIN", "C", "C++", "Python", "KOTLIN"};
    private int mTouchSlop;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        //  WindowManager windowManager=getSystemService(Context.WINDOW_SERVICE.class);
        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
//        Window window=getWindow().getContainer()
        initView();
    }

    /**
     *
     */
    float startY = 0;
    float mCurrentY;
    int direction = 0;
    boolean isShow = false;

    private void initView() {
        lvAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, programs);
        barView = findViewById(R.id.titleBarView);
//        lv_test = findViewById(R.id.lv_test);
//        lv_test.setAdapter(lvAdapter);
        View headView = new View(this);

        headView.post(new Runnable() {
            @Override
            public void run() {
                headView.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, (int) getResources().getDimension(R.dimen.pub_15dp)));
            }
        });

//        lv_test.addHeaderView(headView);
        mTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();

//        circleView=findViewById(R.id.circleView);
        barView.setLeftListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ViewTestActivity.this, "点击了左侧按钮", Toast.LENGTH_SHORT).show();
                barView.setButtonVisible(0, false);
//                barView.setButtonVisible(1,true);
            }
        });

        barView.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                barView.setButtonVisible(1, false);

                Toast.makeText(ViewTestActivity.this, "点击了右侧按钮", Toast.LENGTH_SHORT).show();
            }
        });

//        lv_test.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//
//                        startY = event.getY();
//                        Log.d("TAG", "startY " + startY);
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        mCurrentY = event.getY();
//                        if (mCurrentY - startY > mTouchSlop) {
//                            direction = 0;
//                        } else if (startY - mCurrentY > mTouchSlop) {
//                            direction = 1;
//                        }
//
//                        Log.d("TAG", "startY " + startY);
//                        Log.d("TAG", "mCurrentY " + mCurrentY);
//
//                        if (direction == 0) {
//                            if(!isShow){
//                                toAnim(0);
//                                isShow=!isShow;
//                            }
//
//
//                        } else if (direction == 1) {
//                            if(isShow){
//                                toAnim(1);
//                                isShow=!isShow;
//                            }
//
//                        }
//
//                        break;
//                }
//
//
//                return false;
//            }
//        });

//        lv_test.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//
//            }
//        });

//        circleView.setSweepValue(90);

    }

    Animator animator;

    private void toAnim(int direction) {
        Log.d("TAG", "toAnim " + direction);
        if (null != animator && animator.isRunning()) {
            animator.cancel();
        }

        switch (direction) {
            //down
            case 0:
                animator = ObjectAnimator.ofFloat(barView, "translationY", barView.getTranslationY(), 0);
                barView.setVisibility(View.VISIBLE);
                break;
            //up
            case 1:
                animator = ObjectAnimator.ofFloat(barView, "translationY", barView.getTranslationY(), -barView.getHeight());
                barView.setVisibility(View.GONE);
                break;
        }
        animator.start();


    }

    public void toPictureController(View v) {
        PicControllerFragment picControllerFragment = new PicControllerFragment();
        FragmentManager manager = getSupportFragmentManager();
        picControllerFragment.show(manager, PicControllerFragment.class.getName());
    }

    public void toSurFaceView(View view) {
        Intent intent = new Intent(this, SurfaceViewActivity.class);
        startActivity(intent);

    }


}
