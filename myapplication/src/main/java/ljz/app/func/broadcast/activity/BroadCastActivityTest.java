package ljz.app.func.broadcast.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import ljz.app.R;
import ljz.app.func.broadcast.receiver.BaseReceiver;
import ljz.app.func.broadcast.receiver.LocalReceiver;
import ljz.app.func.broadcast.receiver.StaticReceiver;

/**
 * 项目名称：LJZ_DEMO
 *
 * @author LJZ ljz@yitong.com.cn
 * Created Time  2019/8/26 16:12.
 * @version 1.0
 */
public class BroadCastActivityTest extends AppCompatActivity {
    BaseReceiver baseReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);
    }


    public void sendStaticReceiver(View view) {
        baseReceiver = new StaticReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.ljz.local");
        registerReceiver(baseReceiver, intentFilter, "com.ljz.receiver", null);
        Intent intent = new Intent();
        intent.setAction("com.ljz.local");
        intent.putExtra("DATA", "THIS IS A STATIC RECEIVER !");
        sendBroadcast(intent);
    }

    public void sendOrderReceiver(View view) {
        baseReceiver = new StaticReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.ljz.local");
        registerReceiver(baseReceiver, intentFilter, "com.ljz.receiver", null);
        Intent intent = new Intent();
        intent.setAction("com.ljz.local");
        intent.putExtra("DATA", "THIS IS A STATIC RECEIVER !");
        sendBroadcast(intent);
    }

    public void sendLocalReceiver(View view) {
        baseReceiver = new LocalReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.ljz.local");
        LocalBroadcastManager.getInstance(this).registerReceiver(baseReceiver, intentFilter);
        Intent intent = new Intent();
        intent.setAction("com.ljz.local");
        intent.putExtra("DATA", "THIS IS A LOCAL RECEIVER !");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != baseReceiver) {
            if(baseReceiver instanceof LocalReceiver){
                LocalBroadcastManager.getInstance(this).unregisterReceiver(baseReceiver);
            }else {
                unregisterReceiver(baseReceiver);
            }

        }

    }
}
