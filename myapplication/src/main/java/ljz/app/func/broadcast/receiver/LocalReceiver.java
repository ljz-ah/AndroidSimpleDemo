package ljz.app.func.broadcast.receiver;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 项目名称：LJZ_DEMO
 *
 * @author LJZ ljz@yitong.com.cn
 * Created Time  2019/9/10 16:52.
 * @version 1.0
 */
public class LocalReceiver extends BaseReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e(TAG,"Action"+intent.getAction());

        Log.e(TAG,"intent"+intent);

        String data=intent.getStringExtra("DATA");
        Log.e(TAG,"intent "+data);
    }
}
