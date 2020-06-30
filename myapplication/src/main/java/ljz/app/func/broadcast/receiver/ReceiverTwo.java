package ljz.app.func.broadcast.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 项目名称：LJZ_DEMO
 *
 * @author LJZ ljz@yitong.com.cn
 * Created Time  2019/8/27 9:15.
 * @version 1.0
 */
public class ReceiverTwo extends BroadcastReceiver {
    public  final String TAG=this.getClass().getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG,"Action"+intent.getAction());

        Log.e(TAG,"intent"+intent);

        Log.e(TAG,"intent "+intent.getStringExtra("DATA"));

    }
}
