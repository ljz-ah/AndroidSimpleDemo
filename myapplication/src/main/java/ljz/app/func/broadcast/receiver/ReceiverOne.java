package ljz.app.func.broadcast.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * 项目名称：LJZ_DEMO
 *
 * @author LJZ
 * Created Time  2019/8/27 9:14.
 * @version 1.0
 */
public class ReceiverOne extends BroadcastReceiver{
    public  final String TAG=this.getClass().getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG,"Action"+intent.getAction());

        Log.e(TAG,"intent"+intent);

        Log.e(TAG,"intent "+intent.getStringExtra("DATA"));




        Bundle bundle=getResultExtras(false);
//        if(null!=bundle){
//            Log.e(TAG,"DATA "+bundle.get("DATA"));
//
//            bundle.putString("DATA",bundle.get("DATA")+"  ONE 路过 ");
//            setResultExtras(bundle);
//        }


    }
}
