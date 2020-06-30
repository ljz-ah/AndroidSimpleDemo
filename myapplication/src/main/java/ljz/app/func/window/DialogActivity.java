package ljz.app.func.window;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.Nullable;

import ljz.app.R;
import ljz.app.func.utils.DialogUtils;

/**
 * 项目名称：LJZ_DEMO
 *
 * @author LJZ ljz@yitong.com.cn
 * Created Time  2019/9/18 10:11.
 * @version 1.0
 */
public class DialogActivity extends AppCompatActivity {
    private Button alertDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        initView();
    }
    View view;
    private void initView(){
        alertDialog=findViewById(R.id.alertDialog);
        view =LayoutInflater.from(DialogActivity.this).inflate(R.layout.dialog_layout,null,false);
        final View contentView=LayoutInflater.from(DialogActivity.this).inflate(R.layout.diaolog_gift_tip,null,false);
        ImageView iv_screen=view.findViewById(R.id.iv_screen);
        ImageView iv_close=contentView.findViewById(R.id.iv_close);
        iv_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtils.getInstance().closeDialog();
            }
        });
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtils.getInstance().closeDialog();
            }
        });
        alertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogUtils.getInstance().createDialog(DialogActivity.this,view,-1);
                DialogUtils.getInstance().createDialog(DialogActivity.this,contentView,0);

            }
        });

    }


    /**
     *
     * @param v
     */
    public void alertDialog(View v) {




//        CustomDialogImple dialogImple=new CustomDialogImple(this);
//        getDialog(this,1);
//
//        getDialog(this,1);


//        dialogImple.setContentView(view);
////        dialogImple.setTitle("测试");
//        dialogImple.show();
//
//        CustomDialogImple dialogImpleOne=new CustomDialogImple(this);
//        View view1=LayoutInflater.from(this).inflate(R.layout.dialog_layout,null,false);
//        dialogImpleOne.setContentView(view1);
////        dialogImple.setTitle("测试");
//        dialogImpleOne.show();

//        AlertDialog.Builder dialog=new AlertDialog.Builder(this).setMessage("this is alertDialog").setTitle("TEST").setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
//        dialog.create().show();
    }

    /**
     *
     * @param v
     */
    public void popWindow(View v) {
        PopupWindow popupWindow=new PopupWindow();
        popupWindow.setWidth(200);
        popupWindow.setHeight(500);
        popupWindow.setFocusable(true);
        View view= LayoutInflater.from(this).inflate(R.layout.dialog_layout,null,false);
//        popupWindow.setContentView(view);
        popupWindow.showAsDropDown(view,100,100);



    }

    /**
     *
     * @param v
     */
    public void actyvity(View v) {

    }
    private void getDialog(Context context, int wdStyle) {

        View view=LayoutInflater.from(this).inflate(R.layout.dialog_layout,null,false);

        CustomDialogImple dialog = new CustomDialogImple(context);
        switch (wdStyle) {
            case 0:
                dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
                break;
            case 1:
                dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                break;

            default:
                dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                break;
        }

        Window window = dialog.getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.gravity = Gravity.CENTER;
        dialog.onWindowAttributesChanged(wl);

       dialog.show();
    }
}
