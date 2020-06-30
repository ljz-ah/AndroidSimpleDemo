package ljz.app.func.animator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import ljz.app.R;

/**
 * 项目名称：LJZ_DEMO
 *
 * @author LJZ
 * Created Time  2019/9/23 13:57.
 * @version 1.0
 */
public class AnimSimpleActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actiivty_anim);
    }



    public void propertyAnimation(View v){

        startActivity(new Intent(this,PropertyAnimationActivity.class));

    }



    public void viewAnimation(View v){

        startActivity(new Intent(this,ViewAnimActivity.class));

    }


}
