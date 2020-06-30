package ljz.app.func.utils.utils;


import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;


/**
 * Created by cib on 17-1-9.
 */
public class PerMissionUtis  {


    /**
     * 检查是否拥有使用权
     * @param mcontext
     * @param permission
     * @return
     */
    public  static boolean isGranted(Context mcontext,String permission){
        return !isMardhmallow()||isGranted_(permission,mcontext);

    }

    /**
     * 检查当前手机android版本是否是6.0
     * @return
     */
    public static  boolean isMardhmallow(){
        return Build.VERSION.SDK_INT>=Build.VERSION_CODES.M;


    }

    /**
     * 检查当前权限是否申请了
     * @param persMission
     * @param context
     * @return
     */
    public static boolean isGranted_(String persMission,Context context){
        int checkSelfPermission= ActivityCompat.checkSelfPermission(context,persMission);
        return  checkSelfPermission== PackageManager.PERMISSION_GRANTED;

    }




}
