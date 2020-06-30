package ljz.app.func.animator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import ljz.app.R;

/**
 * @ClassName ViewAnimActivity
 * @Description TODO
 * @Author LJZ
 * @Date 2020/6/16 16:57
 * @Version 1.0
 */
public class ViewAnimActivity extends Activity {
    private ImageView iv_photo;
    private Button btn_anim_translate, btn_anim_rotate, btn_anim_alpha;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewanim);

    }

    private void initView() {

    }

    /**
     * 旋转动画
     *
     * @param v
     */
    public void rotateAnim(View v) {
//        RotateAnimation animation = new RotateAnimation(0, 180, 100, 100);
        RotateAnimation animation = new RotateAnimation(0, 180, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(2000);
        v.startAnimation(animation);
        animation.setFillAfter(true);
    }

    /**
     * 透明动画
     *
     * @param v
     */
    public void alphaAnim(View v) {
        AlphaAnimation animation = new AlphaAnimation(1, 0);
        animation.setDuration(2000);
        v.startAnimation(animation);
        animation.setFillAfter(true);
    }

    /**
     * 移动动画
     *
     * @param v
     */
    public void translateAnim(View v) {
//        TranslateAnimation translateAnimation=new TranslateAnimation(0,100,0,100);
        TranslateAnimation translateAnimation = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.ABSOLUTE, 100, 0, TranslateAnimation.RELATIVE_TO_SELF, TranslateAnimation.RELATIVE_TO_SELF, 0);
        translateAnimation.setDuration(2000);
        v.startAnimation(translateAnimation);
        translateAnimation.setFillAfter(true);
    }

    /**
     * @param v
     */
    public void scaleAnim(View v) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 2f, 0.0f, 2f);
        scaleAnimation.setDuration(2000);
        scaleAnimation.setFillAfter(true);
        v.startAnimation(scaleAnimation);

    }


    public void animList(View view) {
        TranslateAnimation translateAnimation = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF,0, TranslateAnimation.ABSOLUTE,200,TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF,0);
        translateAnimation.setDuration(1000);
        translateAnimation.setFillAfter(true);


        RotateAnimation rotateAnimation = new RotateAnimation(0, 180, 100, 100);
        rotateAnimation.setDuration(1000);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1, 0.5f, 1, 2f);
        scaleAnimation.setDuration(1500);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setDuration(2000);

        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(translateAnimation);
//        animationSet.addAnimation(scaleAnimation);
//        animationSet.addAnimation(rotateAnimation);
//        animationSet.addAnimation(alphaAnimation);
//
//        ScaleAnimation scaleAnimation1 = new ScaleAnimation(0.5f, 1f, 2f, 1f);
//        scaleAnimation1.setDuration(2000);
//        RotateAnimation rotateAnimation1 = new RotateAnimation(180, 0, 100, 100);
//        rotateAnimation1.setDuration(1000);
        TranslateAnimation translateAnimation1 = new TranslateAnimation(TranslateAnimation.ABSOLUTE,0,TranslateAnimation.RELATIVE_TO_SELF, 0,TranslateAnimation.RELATIVE_TO_SELF,0, TranslateAnimation.RELATIVE_TO_SELF,0);
        translateAnimation1.setDuration(1000);
        translateAnimation1.setFillAfter(true);
//        AlphaAnimation alphaAnimation1 = new AlphaAnimation(0, 1);
//        alphaAnimation1.setDuration(1000);
//

//        animationSet.addAnimation(scaleAnimation1);
//        animationSet.addAnimation(rotateAnimation1);
        animationSet.addAnimation(translateAnimation1);
//        animationSet.addAnimation(alphaAnimation1);
        animationSet.setFillAfter(true);
        view.startAnimation(animationSet);


    }


}
