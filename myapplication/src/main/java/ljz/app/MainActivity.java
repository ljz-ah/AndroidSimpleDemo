package ljz.app;

import android.content.Intent;
import android.util.Log;
import android.view.View;

/**
 * @author 之
 */
public class MainActivity extends BaseActivity {
    public static String TAG = "MainActivity";

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "------------>onStart");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "------------>onResume");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "------------>onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "------------>onPause");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "------------>onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "------------>onDestroy");
    }


    public void toActivity(View view){
        startActivity(new Intent(MainActivity.this,TwoActivity.class));

    }

    @Override
    public int getLayout() {
        return R.layout.diaolog_gift_tip;
    }



    @Override
    public void initData() {

    }
}
