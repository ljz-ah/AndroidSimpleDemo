package ljz.app.func.window;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * 项目名称：xwydzf-android-ci-appMerge-ytxupdata
 *
 * @author LJZ ljz@yitong.com.cn
 * Created Time  2019/9/19 17:42.
 * @version 1.0
 */
public class CustomDialogImple extends Dialog {
    private LinkedHashMap<Integer, Dialog> linkedHashSet = new LinkedHashMap<>();
    LinkedList<Dialog>dialogLinkedList=new LinkedList<>();
    private Dialog curDialog;
    private int viewID;

    public CustomDialogImple(@NonNull Context context) {
        super(context);
        curDialog = this;
        dialogLinkedList.add(this);
    }

    public CustomDialogImple(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        curDialog = this;
    }

    protected CustomDialogImple(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        curDialog = this;
    }


    @Override
    public void setContentView(@NonNull View view, @Nullable ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        viewID=view.getId();

        linkedHashSet.put(view.getId(), curDialog);
        Log.e("TAG","setContentView "+curDialog);
    }

    @Override
    public void setContentView(@NonNull View view) {
        super.setContentView(view);
        viewID=view.getId();
//        Dialog dialog=linkedHashSet.get(view.getId());
        linkedHashSet.put(view.getId(), curDialog);

    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        viewID=layoutResID;
        linkedHashSet.put(layoutResID, curDialog);
    }

    @Override
    public void show() {
        Log.e("TAG","show "+curDialog.getWindow().toString());

        super.show();

    }

    @Override
    public void dismiss() {
        for (int i=0;i<dialogLinkedList.size();i++){
            super.dismiss();
        }

//        super.dismiss();
//        curDialog.getWindow().getDecorView().getId();
        Log.e("TAG","viewID "+viewID);
//        Dialog dialog=linkedHashSet.get(viewID);
//        if(null!=dialog){
//            super.dismiss();
////          dialog.dismiss();
//        }
    }
}
