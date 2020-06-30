package ljz.app.func.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

/**
 *项目名称：LJZ_DEMO
 *@author LJZ
 *Created Time  2019/6/13 10:13.
 *@version 1.0
 */
class TestService : Service() {
    val TAG = "TestService"

    override fun onBind(intent: Intent?): IBinder {
        Log.e(TAG, "------------>onBind")
        return MyBinder();
    }

    class MyBinder : Binder() {

        fun getService(): TestService {
            return TestService();
        }

    }


    override fun onCreate() {
        super.onCreate()
        Log.e(TAG, "------------>onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
        Log.e(TAG, "------------>onStartCommand")
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        Log.e(TAG, "------------>onRebind")
    }


    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
        Log.e(TAG, "------------>onUnbind")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "------------>onDestroy")
    }

}
