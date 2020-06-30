package ljz.app

import android.os.Bundle
import android.os.PersistableBundle

import androidx.appcompat.app.AppCompatActivity

/**
 *项目名称：LJZ_DEMO
 *@author LJZ
 *Created Time  2020/4/22 13:16.
 *@version 1.0
 */
abstract class BaseActivity : AppCompatActivity() {

    /**
     * 获取原生布局
     */
    abstract fun getLayout(): Int


    /**
     * 初始化数据
     */
    abstract fun initData()
}