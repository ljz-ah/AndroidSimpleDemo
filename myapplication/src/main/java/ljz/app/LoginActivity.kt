package ljz.app

import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.PopupWindow
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.PopupWindowCompat.showAsDropDown
import kotlinx.android.synthetic.main.activity_login.*

/**
 *项目名称：LJZ_DEMO
 *@author LJZ
 *Created Time  2020/3/28 21:00.
 *@version 1.0
 */
public class LoginActivity : AppCompatActivity() {
    // var tv_selector: TextView? = null
//    var btn_login: Button? = null
//    var edt_name: EditText? = null
//    var edt_pwd: EditText? = null
    var lv_account: ListView? = null
    lateinit var names: ArrayList<String>;

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initOnClick()
        names = ArrayList();
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun initOnClick() {

        tv_selector.setOnClickListener {

            showPopWindow()
        }

        btn_login.setOnClickListener {

            names.add(edt_name.text.toString())
        }


    }


    @RequiresApi(Build.VERSION_CODES.M)
    fun showPopWindow() {
        var popView: View
        popView = LayoutInflater.from(this).inflate(R.layout.account_select_popwindow, null)
        lv_account = popView.findViewById(R.id.lv_account)
        initAdapter();

        var accountPop = PopupWindow(popView);
        accountPop.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT)
        //设置弹出窗体的高
        accountPop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
        accountPop.setBackgroundDrawable(ColorDrawable(getColor(R.color.colorPrimary)))
        accountPop.setOutsideTouchable(true)
        accountPop.setFocusable(true)//获取焦点，在点击物理返回键的时候可以隐藏popwindow


        if (accountPop.isShowing) {
            accountPop.dismiss()

        }
        if (Build.VERSION.SDK_INT >= 24) {
            showAsDropDown(accountPop, edt_name, 0, 0, Gravity.TOP or Gravity.CENTER);
        }


    }

    fun initAdapter() {

        var accountAdapter: BaseAdapter? = null;
        accountAdapter?.let { accountAdapter!!.notifyDataSetChanged() }
        accountAdapter ?: let {
            accountAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, names)
            lv_account?.adapter = accountAdapter;
        }


//        if(accountAdapter?:ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, names))


        lv_account?.setOnItemClickListener { _, view, position, id ->
            var accountStr: String
            accountStr = names.get(position)

            edt_name.setText(accountStr)

        }


    }

}