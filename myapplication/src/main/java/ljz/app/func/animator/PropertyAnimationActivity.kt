package ljz.app.func.animator

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import ljz.app.R

/**
 * 项目名称：LJZ_DEMO
 *
 * @author LJZ ljz@yitong.com.cn
 * Created Time  2019/9/23 14:04.
 * @version 1.0
 */
class PropertyAnimationActivity : Activity() {
    internal var startX = 0f
    internal var toX = -100f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_propertyanim)
    }

    fun rotateAnim(v: View) {
        Log.e("TAG", "before v getRotationX  X " + v.rotationX + " getRotationy  y" + v.rotationY)
        Log.e("TAG", "v getX  X " + v.x)

        var rotateAnim = getObjectAnimator(v, "rotation", 0.0F, 180.0F, 90F, 0F)
        rotateAnim.duration = 1500
        v.pivotX=1.5f;
        v.pivotY=0.5f;

        //        ObjectAnimator translationX = translationX(v, startX,toX);
        //        ObjectAnimator alphaAnim=ObjectAnimator.ofFloat(v,"alpha",0,0.3f,0.5f,1,0.9f,0.5f,0.3f,0).setDuration(3000);


        //        if(startX!=0f){
        //            translationX=translationX(v,startX,startX-100);
        //        }
        //
        //        translationX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
        //            @Override
        //            public void onAnimationUpdate(ValueAnimator animation) {
        //
        //                startX= (float) animation.getAnimatedValue();
        //
        //                Log.e("TAG", "getX "+(Float)animation.getAnimatedValue());
        //
        //            }
        //        });

        val set = AnimatorSet()

        set.play(rotateAnim)
        set.start()

        //        objectAnimator.start();
        //        objectAnimatorTrans.start();
        Log.e("TAG", "v after  X " + v.pivotX)
        Log.e("TAG", "v after  Y " + v.pivotY)
        //        ObjectAnimator objectAnimator=new ObjectAnimator();
        //        objectAnimator.setPropertyName("rotation");
        //        objectAnimator.setTarget(v);
        //        objectAnimator.setIntValues(0,180);
        //        objectAnimator.setDuration(500);
        //
        //        objectAnimator.start();


    }

    fun alphaAnim(v: View) {
        var alphaAnimatorX = getObjectAnimator(v, "alpha", 1f, 0.9f, 0.5f, 0.1f, 0f, 0.8f, 0.7f, 1f)
        alphaAnimatorX.duration = 2000
        alphaAnimatorX.start()
    }

    fun zoomAnim(v: View) {
        var zoomAnimatorX = getObjectAnimator(v, "scaleX", 1f, 2f, 3f, 2f, 1f)
        var zoomAnimatorY = getObjectAnimator(v, "scaleY", 1f, 2f, 3f, 2f, 1f)
        var zoomAnimatorZ = getObjectAnimator(v, "scaleZ", 1f, 2f, 3f, 2f, 1f)
        val set = AnimatorSet()


        set.play(zoomAnimatorX).before(zoomAnimatorY).before(zoomAnimatorZ)
        set.duration = 1500
        set.start()


    }

    /**
     * 属性动画-移动动画
     */
    fun translateAnim(v: View) {
        var translationX = getObjectAnimator(v, "translationX", 0f, 10f, 20f, 30f, 40f, 100f, 40f, 30f, 20f, 10f, 0f)
        var translationY = getObjectAnimator(v, "translationY", 0f, 10f, 20f, 30f, 40f, 50f, 40f, 30f, 20f, 10f, 0f)
        val set = AnimatorSet()
        set.play(translationY).before(translationX)
        set.duration = 1500
        set.start()

//        var animator:ObjectAnimator= ObjectAnimator.ofFloat(v,"translationX",300f);
//        animator.duration=1500
//        animator.start()

    }


    fun translationX(view: View, vararg values: Float): ObjectAnimator {

        return ObjectAnimator.ofFloat(view, "translationX", *values).setDuration(500)
    }

    fun getObjectAnimator(view: View, propertyName: String, vararg values: Float): ObjectAnimator {
        return ObjectAnimator.ofFloat(view, propertyName, *values).setDuration(500)
    }


}
