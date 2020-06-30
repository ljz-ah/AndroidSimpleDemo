package ljz.app.func.utils;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import ljz.app.R;

/**
 * 项目名称：LJZ_DEMO
 *
 * @author LJZ ljz@yitong.com.cn
 * Created Time  2019/9/19 19:34.
 * @version 1.0
 */
public class DialogUtils {
    public static String TAG = "DialogUtils";
    private LinkedHashMap<Integer, Dialog> linkedHashSet = new LinkedHashMap<>();
    private ArrayList<Dialog> dialogs = new ArrayList<>();
    private static DialogUtils dialogUtils;

    public static DialogUtils getInstance() {
        if (dialogUtils == null) {
            dialogUtils = new DialogUtils();
        }
        return dialogUtils;
    }



    public synchronized void createDialog(Context context, View view, int wdStyle) {
//           Dialog  inTheListDialog=linkedHashSet.get();
//        if(null==inTheListDialog){
        Dialog dialog  = new Dialog(context, R.style.CustomDialog);

        dialogs.add(dialog);
//        }else {
//            dialog=inTheListDialog;
//        }
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
        wl.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        wl.gravity = Gravity.CENTER;
        dialog.onWindowAttributesChanged(wl);
        Log.e(TAG, " dialog " + dialog);
        dialog.show();
    }


    public void closeDialog(){
        for(Dialog curDialog:dialogs){
            curDialog.dismiss();
        }
        dialogs.clear();
    }


    public static void main(String[] args){


    }

}
