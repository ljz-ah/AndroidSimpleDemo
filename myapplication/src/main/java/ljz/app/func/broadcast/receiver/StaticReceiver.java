package ljz.app.func.broadcast.receiver;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 项目名称：LJZ_DEMO
 *
 * @author LJZ ljz@yitong.com.cn
 * Created Time  2019/8/26 16:21.
 * @version 1.0
 */
public class StaticReceiver extends BaseReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e(TAG,"Action"+intent.getAction());

        Log.e(TAG,"intent"+intent);

        Log.e(TAG,"intent "+intent.getStringExtra("DATA"));


    }
}