package ljz.app;

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hailong.biometricprompt.fingerprint.FingerprintCallback
import com.hailong.biometricprompt.fingerprint.FingerprintVerifyManager
import ljz.app.Interface.WinCallback
import ljz.app.func.animator.AnimSimpleActivity
import ljz.app.func.broadcast.activity.BroadCastActivityTest
import ljz.app.func.broadcast.receiver.LocalReceiver
import ljz.app.func.serialize.TestSerializeActivity
import ljz.app.func.utils.utils.PermissionManager
import ljz.app.func.utils.utils.PermissionManager.PermissionCheck
import ljz.app.func.view.ViewTestActivity
import ljz.app.func.window.DialogActivity


/**
 * 项目名称：LJZ_DEMO
 *
 * @author LJZ
 * Created Time  2019/9/18 10:38.
 * @version 1.0
 */
public class HomeActivity : AppCompatActivity() {
    lateinit var windowObj: Window;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        windowObj=window
        iniData()
    }


//    var String[] persions;


    //    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_home);
//        iniData();
//    }
//
    fun iniData() {

        if (!PermissionManager.CheckPermissionAtFunc(this, object : PermissionCheck(Manifest.permission.WRITE_EXTERNAL_STORAGE, "写入SDK卡", null) {
                    override fun goFunc() {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }
                })) {
            finish();
        }

//            (Manifest.permission.WRITE_EXTERNAL_STORAGE, "写入SDK卡", null)

//        PermissionManager.PermissionCheck mCheck = new PermissionManager.PermissionCheck(Manifest.permission.WRITE_EXTERNAL_STORAGE, "写入SDK卡", null) {
//            @Override
//            public void goFunc() {
//            }
//        };
//        if (!PermissionManager.CheckPermissionAtFunc(this, mCheck)) {
//            finish();
//        }

        var px: Int = dip2px(this, 218F);
        Log.e("TAG", "px " + px)
//        Toast.makeText(this,px,Toast.LENGTH_SHORT).show();

        val homeFilter = IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)
        val locaReceviver = LocalReceiver()

        registerReceiver(locaReceviver, homeFilter)
        val win: Window = window

        Log.e("TAG", "view---->"+win.enterTransition)

//        Log.e(FragmentActivity.TAG, "touchOnclick: activity=$activity")
        win.setCallback(object : WinCallback(win.getCallback()) {
            override fun dispatchTouchEvent(event: MotionEvent): Boolean {

                Log.e("TAG", "dispatchTouchEvent---" + event.action)
                when (event.action) {
                    MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> Log.e("TAG", "dispatchTouchEvent:activity窗口被触摸")
                    MotionEvent.ACTION_UP -> Log.e("TAG", "dispatchTouchEvent:手指离开activity窗口")
                }
                return super.dispatchTouchEvent(event)
            }

            override fun onWindowFocusChanged(hasFocus: Boolean) {
                super.onWindowFocusChanged(hasFocus)
                Log.e("TAG", "窗口焦点变化")
                Log.e("TAG", "view----窗口焦点变化>"+windowObj.decorView.windowId.isFocused);
            }

            override fun onDetachedFromWindow() {
                super.onDetachedFromWindow()
                Log.e("TAG", "上层窗口消失")
            }

            override fun onCreatePanelView(featureId: Int): View? {
                Log.e("TAG", "onCreatePanelView")
                return super.onCreatePanelView(featureId)
            }

            override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
                if (event != null) {
                    Log.e("TAG", "dispatchKeyEvent---" + event.action)
                }
                return super.dispatchKeyEvent(event)
            }

            override fun dispatchPopulateAccessibilityEvent(event: AccessibilityEvent?): Boolean {
                Log.e("TAG", "dispatchPopulateAccessibilityEvent")
                return super.dispatchPopulateAccessibilityEvent(event)

            }

            override fun onWindowAttributesChanged(attrs: WindowManager.LayoutParams?) {
                Log.e("TAG", "onWindowAttributesChanged")
                super.onWindowAttributesChanged(attrs)
            }

            override fun onPanelClosed(featureId: Int, menu: Menu) {
                Log.e("TAG", "onPanelClosed")
                super.onPanelClosed(featureId, menu)
            }

            override fun onActionModeFinished(mode: ActionMode?) {
                Log.e("TAG", "onActionModeFinished")
                super.onActionModeFinished(mode)
            }

            override fun onActionModeStarted(mode: ActionMode?) {
                Log.e("TAG", "onActionModeStarted")
                super.onActionModeStarted(mode)
            }

            override fun dispatchGenericMotionEvent(event: MotionEvent?): Boolean {
                if (event != null) {
                    Log.e("TAG", "dispatchGenericMotionEvent---" + event.action)
                }
                return super.dispatchGenericMotionEvent(event)
            }

        })


    }


    fun startDialogActivity(v: View) {
        startActivity(Intent(this, DialogActivity::class.java))
    }

    fun startAnimActivity(v: View) {
        startActivity(Intent(this, AnimSimpleActivity::class.java))
    }

    fun startBroadCastReceiverActivity(v: View) {
        startActivity(Intent(this, BroadCastActivityTest::class.java));
    }

    fun startSeializ(v: View) {
        startActivity(Intent(this, TestSerializeActivity::class.java));


    }

    fun startCustomeView(v: View) {
        startActivity(Intent(this, ViewTestActivity::class.java));


    }

    fun startKtolinClas(v: View) {
        startActivity(Intent(this, KotlinActivity::class.java));


    }

    fun startTwoActivity(v: View) {
        startActivity(Intent(this, TwoActivity::class.java));


    }


    fun startLogin(v: View) {
        startActivity(Intent(this, LoginActivity::class.java));
    }

    fun strtNetRequst(v: View) {

        startActivity(Intent(this, RequestActivity::class.java));
    }


    fun startFingerPrint(v: View) {

        val builder = FingerprintVerifyManager.Builder(this)
        Log.e("TAG", "view---->"+windowObj.decorView);
        Log.e("TAG", "view----1111>"+windowObj.decorView.windowId.isFocused);
        windowObj.decorView.rootView.removeOnAttachStateChangeListener(object :View.OnAttachStateChangeListener{
            override fun onViewDetachedFromWindow(v: View?) {
                Log.d("TAG", "onViewDetachedFromWindow")
            }

            override fun onViewAttachedToWindow(v: View?) {
                Log.d("TAG", "onViewAttachedToWindow")
            }

        });
        builder.enableAndroidP(true)
        builder.callback(object : FingerprintCallback {
            override fun onFailed() {

                Log.d("TAG", "onFailed")
                Log.e("TAG", "view----onFailed>"+windowObj.decorView.windowId.isFocused);

                Log.e("TAG", "view---->"+windowObj.container)
            }

            override fun onCancel() {
                Log.d("TAG", "onCancel")
            }

            override fun onSucceeded() {
                Log.d("TAG", "onSucceeded")
                Log.e("TAG", "onSucceeded  "+windowObj.decorView.windowId.isFocused);
            }

            override fun onUsepwd() {
                Log.d("TAG", "onUsepwd")
            }

            override fun onNoneEnrolled() {
                Log.d("TAG", "onNoneEnrolled")
            }

            override fun onHwUnavailable() {
                Log.d("TAG", "onHwUnavailable")
            }


        }).build()

        var win: Window = window
        win.decorView.addOnLayoutChangeListener(object :View.OnLayoutChangeListener{
            override fun onLayoutChange(v: View?, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) {
                Log.d("TAG", "onLayoutChange")
//                TODO("Not yet implemented")
            }
        }
        )





    }
//
//    public void startDialogActivity(View v) {
//        startActivity(new Intent(this, DialogActivity.class));
//    }
//
//    public void startAnimActivity(View v) {
//        startActivity(new Intent(this, AnimSimpleActivity.class));
//    }
//
//    public void startBroadCastReceiverActivity(View v) {
//        startActivity(new Intent(this, BroadCastActivityTest.class));
//    }
//
//    public void startSeializ(View v) {
//        startActivity(new Intent(this, TestSerializeActivity.class));
//
//
//    }
//
//    public void startCustomeView(View v) {
//        startActivity(new Intent(this, ViewTestActivity.class));
//
//
//    }

    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.getResources().getDisplayMetrics().density

        Log.e("TAG", "scale " + scale)
        return (dpValue * scale + 0.5f).toInt()
    }

}
