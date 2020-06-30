package ljz.app.func.broadcast.receiver;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * 项目名称：LJZ_DEMO
 *
 * @author LJZ
 * Created Time  2019/8/27 9:16.
 * @version 1.0
 */
public class ReceiverFour extends BaseReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG,"Action"+intent.getAction());

        Log.e(TAG,"intent"+intent);

        String data=intent.getStringExtra("DATA");
        Log.e(TAG,"intent "+data);
        Bundle bundle=new Bundle();
//        if(null!=data&&!data.equals("")){
//
//            bundle.putString("DATA",data+" FOUR 路过 ");
//            setResultExtras(bundle);
//        }


    }
}
