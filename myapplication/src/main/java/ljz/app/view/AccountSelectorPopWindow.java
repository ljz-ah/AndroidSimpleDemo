package ljz.app.view;

import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：LJZ_DEMO
 *
 * @author LJZ ljz@yitong.com.cn
 * Created Time  2020/3/28 21:28.
 * @version 1.0
 */
public class AccountSelectorPopWindow extends PopupWindow {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("HELLO");
        list.add("HELLO1");
        list.add("HELLO2");
       list.set(0,"HELLO3");
    System.out.print("LIST size  "+list.size());

    for (String str:list){
        System.out.print("LIST element "+str);
    }


    }

}
