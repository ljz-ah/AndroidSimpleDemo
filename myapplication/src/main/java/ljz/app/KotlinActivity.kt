package ljz.app

import android.content.Intent
import android.os.Bundle

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ljz.app.func.service.TestService

//package org.kotlinlang.play
/**
 *项目名称：LJZ_DEMO
 *@author LJZ
 *Created Time  2019/6/13 10:29.
 *@version 1.0
 */
class KotlinActivity : AppCompatActivity() {


    var btn_bindService: Button? = null
    var btn_unbindService: Button? = null;

    companion object {
        const val TAG = "KotlinActivity"
    }

    var testService: TestService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "----------->onCreate")
        setContentView(iniView())
        iniView()
        initAction()
    }


    fun iniView(): View {
        var lly = LinearLayout(this)
        btn_bindService = Button(this)
        btn_unbindService = Button(this)
        lly.orientation = LinearLayout.VERTICAL

        btn_bindService!!.width = 50
        btn_bindService!!.height = 40
        btn_bindService!!.text = "绑定服务"
        btn_unbindService!!.width = 50
        btn_unbindService!!.height = 50
        btn_unbindService!!.text = "解绑服务"

        lly.addView(btn_bindService, 0)
        lly.addView(btn_unbindService, 1)
        return lly
    }

    /**
     */
    private fun initAction() {
        btn_bindService?.setOnClickListener {
            Toast.makeText(this, btn_unbindService?.text, Toast.LENGTH_SHORT).show()
        }

        btn_unbindService?.setOnClickListener(View.OnClickListener {

            Toast.makeText(this, btn_unbindService?.text, Toast.LENGTH_SHORT).show()


        })

    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "----------->onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "----------->onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(TAG, "----------->onRestart")
    }


    override fun onPause() {
        super.onPause()
        Log.e(TAG, "----------->onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "----------->onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "----------->onDestroy")
    }

    /**
     *
     */
    fun initData() {
        var serviceIntent = Intent()
        serviceIntent.setClass(this, TestService::class.java)
        startService(serviceIntent)


//        serviceIntent.setClass(this, TestService.class));
//               this.startService (serviceIntent)

    }


}