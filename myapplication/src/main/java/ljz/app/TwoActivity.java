package ljz.app;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ljz.app.func.service.TestServiceOne;

/**
 * 项目名称：LJZ_DEMO
 *
 * @author LJZ
 * Created Time  2019/6/13 9:28.
 * @version 1.0
 */
public class TwoActivity extends AppCompatActivity {
    public static String TAG = "TwoActivity";
    private TestServiceOne testService;
    MyServiceConn conn;
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "------------>onCreate");
//        ImageView imageView = new ImageView(this);
//        imageView.setImageResource(R.mipmap.huiger);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        conn = new MyServiceConn();
        setContentView(R.layout.activity_two);
    }

    public void onBindService(View v) {
        Intent intent = new Intent();
        intent.setClass(this, TestServiceOne.class);
        bindService(intent, conn, BIND_AUTO_CREATE);

    }

    public void onUnBindService(View v) {

//        if (null != testService) {
        unbindService(conn);
//        }

    }

    public void onStartService(View v) {

        intent = new Intent();
        intent.setClass(this, TestServiceOne.class);
        startService(intent);

    }

    public void onStopService(View v) {
        if (null != intent) {
            stopService(intent);
        }


    }

    public void toActivity(View v) {
//        startActivity(new Intent(TwoActivity.this, KotlinActivity.class));
    }

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

    //    public final static String TAG = "MainActivity";
    public final static int ACTIVITYID = 0X0002;
    //客户端的Messnger
    @SuppressLint("HandlerLeak")
    private Messenger aMessenger = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.arg1 == ACTIVITYID) {
                //客户端接受服务端传来的消息
                Log.d(TAG, "服务端传来了消息=====>>>>>>>");
                String str = (String) msg.getData().get("content");
                Log.d(TAG, str);
            }
        }
    });
    //服务端传来的Messenger
    Messenger sMessenger;

    public class MyServiceConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
//            testService = ((TestServiceOne.LocalBinder) binder).getService();
//            sMessenger = new Messenger(binder);
//
//            Message message = Message.obtain();
//            message.arg1 = 0;
//            //注意这里，把`Activity`的`Messenger`赋值给了`message`中，当然可能你已经发现这个就是`Service`中我们调用的`msg.replyTo`了。
//            message.replyTo = aMessenger;
//
//            Bundle bundle = new Bundle();
//            bundle.putString("content", "我就是Activity传过来的字符串");
//            message.setData(bundle);
//
//            try {
//                //消息从客户端发出
//                sMessenger.send(message);
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }

            IMyAidlInterface aidlInterface = IMyAidlInterface.Stub.asInterface(binder);

            try {
                Log.e(TAG, "aidlInterface getContent " + aidlInterface.sayHellotoClent());
            } catch (RemoteException e) {
                e.printStackTrace();
            }


        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub
            testService = null;
        }
    }

}
