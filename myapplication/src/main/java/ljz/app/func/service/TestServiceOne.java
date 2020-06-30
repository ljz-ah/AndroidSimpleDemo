package ljz.app.func.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import ljz.app.IMyAidlInterface;

/**
 * 项目名称：LJZ_DEMO
 *
 * @author LJZ ljz@yitong.com.cn
 * Created Time  2019/6/13 14:31.
 * @version 1.0
 */
public class TestServiceOne extends Service {

    public static final String TAG = "TestServiceOne";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "------------>onBind");
//        return messenger.getBinder();
        return stub;
    }

    public final class LocalBinder extends Binder {
        public TestServiceOne getService() {
            return TestServiceOne.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "------------>onCreate");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "------------>onStartCommand");
        return super.onStartCommand(intent, flags, startId);

    }


    @Override
    public void onRebind(Intent intent) {
        Log.e(TAG, "------------>onRebind");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "------------>onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "------------>onDestroy");
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.e(TAG, "------------>onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }


    @SuppressLint("HandlerLeak")
    private Messenger messenger = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    Log.d(TAG, "客服端传来的消息===>>>>>>");
                    String str = (String) msg.getData().get("content");
                    Log.d(TAG, str);
                    //发送数据给客户端
                    Message msgTo = Message.obtain();
                    msgTo.arg1 = 0X0002;
                    Bundle bundle = new Bundle();
                    bundle.putString("content", "我是从服务器来的字符串");
                    msgTo.setData(bundle);
                    try {
                        //注意，这里把数据从服务器发出了
                        msg.replyTo.send(msgTo);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;

                default:
                    break;

            }


        }
    });


    private IMyAidlInterface.Stub stub=new IMyAidlInterface.Stub() {
        @Override
        public String sayHellotoClent() throws RemoteException {
            return "HELLO ,CLIENT ! this message frome service  ";
        }
    };

}
