package ljz.app.func.utils.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;


import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

public class PermissionManager {

    private static boolean IsClosePermissionControl = false;

    private static final String TAG = PermissionManager.class.getName();
    private static List<String> listPermissions = new ArrayList<>();
    private static int callbackNum = 0x10;

    public interface IPermissionsHandler {
        void onPermissionResult(int requestCode, String[] permissions, int[] grantResults);
    }

    public interface IPermissionResultReceiver {
        void setPermissionHandler(IPermissionsHandler mHandler);
    }

    /*
    	final String[] strPermissions = new String[]{
             //通讯录
             Manifest.permission.READ_CONTACTS,Manifest.permission.WRITE_CONTACTS,
             //电话
             Manifest.permission.READ_PHONE_STATE,Manifest.permission.CALL_PHONE,
             //相机
             Manifest.permission.CAMERA,
             //位置信息
             Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,
             //存储空间
             Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE,
             //麦克风
             Manifest.permission.RECORD_AUDIO,
             //短信
             Manifest.permission.READ_SMS,Manifest.permission.RECEIVE_SMS,
        };
     */

    public static boolean CheckBasePermissionAtBegin(final Activity mActivity) {
        if (IsClosePermissionControl) {
            return true;
        }

        listPermissions.clear();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        boolean isBasePermissionGranted = true;

        //定位
        if (mActivity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            listPermissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            isBasePermissionGranted = false;
        }
        //存储
        if (mActivity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            listPermissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            isBasePermissionGranted = false;
        }
        //存储
        if (mActivity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            listPermissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            isBasePermissionGranted = false;
        }
        //手机状态
        if (mActivity.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            listPermissions.add(Manifest.permission.READ_PHONE_STATE);
            isBasePermissionGranted = false;
        }
//        //相机
//        if (mActivity.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//            listPermissions.add(Manifest.permission.CAMERA);
//            isBasePermissionGranted = false;
//        }
//        //麦克风
//        if (mActivity.checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
//            listPermissions.add(Manifest.permission.RECORD_AUDIO);
//            isBasePermissionGranted = false;
//        }

        if (isBasePermissionGranted) {
            return true;
        }

        if (listPermissions.size() == 0) {
            return true;
        }

        Object[] objPermissions = listPermissions.toArray();
        String[] strPermissions = new String[objPermissions.length];

        for (int i = 0; i < objPermissions.length; i++) {
            strPermissions[i] = (String) objPermissions[i];
        }

        final int thisCallbackCode = callbackNum++;

        IPermissionsHandler mIPermissionsHandler = new IPermissionsHandler() {
            @SuppressLint("InlinedApi")
            @Override
            public void onPermissionResult(int requestCode, String[] permissions, int[] grantResults) {
                if (requestCode == thisCallbackCode) {
                    List<Boolean> shouldShowRequest = new ArrayList<>();
                    boolean granted = true;
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            granted = false;
                            shouldShowRequest.add(mActivity.shouldShowRequestPermissionRationale(listPermissions.get(i)));
                        }
                    }

                    boolean hasNoShow = false;
                    for (boolean shouldShow :
                            shouldShowRequest) {
//                        Log.d(TAG, "shouldShow", shouldShow + "");
                        if (!shouldShow) {
                            hasNoShow = true;
                        }
                    }

                    if (granted) {
                        Intent mIntent = new Intent();
//                        mIntent.setClass(MyApplication.getInstance().getApplicationContext(), SplashActivity.class);
//                        mActivity.finish();
//                        mActivity.startActivity(mIntent);
                    } else if (!hasNoShow) {
                        new AlertDialog.Builder(mActivity, android.R.style.Theme_DeviceDefault_Light_Dialog_Alert)
                                .setTitle("提示")
                                .setMessage("位置信息，存储空间，电话的权限是必需的，否则无法为您提供服务")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        CheckBasePermissionAtBegin(mActivity);
                                    }
                                })
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        mActivity.finish();
//                                        ActivityTack.getInstanse().exit();
                                    }
                                })
                                .setCancelable(false)
                                .show();
                    } else {
                        new AlertDialog.Builder(mActivity, android.R.style.Theme_DeviceDefault_Light_Dialog_Alert)
                                .setTitle("提示")
                                .setMessage("位置信息，存储空间，电话的权限是必需的，否则无法为您提供服务")
                                .setPositiveButton("开通权限", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent mIntent = new Intent();
                                        mIntent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                        mIntent.setData(Uri.fromParts("package", mActivity.getPackageName(), null));
                                        mActivity.startActivity(mIntent);
                                        mActivity.finish();
//                                        ActivityTack.getInstanse().exit();
                                    }
                                })
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        mActivity.finish();
//                                        ActivityTack.getInstanse().exit();
                                    }
                                })
                                .setCancelable(false)
                                .show();
                    }
                }
            }
        };

        RequestThePermission(mActivity, mIPermissionsHandler, strPermissions, thisCallbackCode);
        return false;
    }

    private static void RequestThePermission(final Activity mActivity, IPermissionsHandler mIPermissionsHandler, String[] strPermissions, int requestCode) {
        if (mActivity instanceof IPermissionResultReceiver) {
            ((IPermissionResultReceiver) mActivity).setPermissionHandler(mIPermissionsHandler);
        }
//        mActivity.requestPermissions(strPermissions,requestCode);
        ActivityCompat.requestPermissions(mActivity, strPermissions, requestCode);
    }

    @SuppressLint("InlinedApi")
    private static void ShowAlertDialog(final Activity mActivity, String strPermissionInMsg) {
        new AlertDialog.Builder(mActivity, android.R.style.Theme_DeviceDefault_Light_Dialog_Alert)
                .setTitle("提示")
                .setMessage(strPermissionInMsg + "的权限是必需的，否则我们无法为您提供相关的服务")
                .setPositiveButton("开通权限", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent mIntent = new Intent();
                        mIntent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        mIntent.setData(Uri.fromParts("package", mActivity.getPackageName(), null));
                        mActivity.startActivity(mIntent);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .show();
    }

    public interface IPermissionCheck {
        String permissionToCheck();

        String msgTip();

        void goFunc();

        IPermissionCheck nextCheck();
    }


    public static abstract class PermissionCheck{
        String strPermission;
        String strMsgTip;
        PermissionCheck nextCheck;
        public PermissionCheck(String strPermission, String strMsgTip, PermissionCheck nextCheck){
            this.strPermission = strPermission;
            this.strMsgTip = strMsgTip;
            this.nextCheck = nextCheck;
        }
        public abstract void goFunc();
    }

    public static boolean CheckPermissionAtFunc(final Activity mActivity, final PermissionCheck mCheck) {
        if (IsClosePermissionControl) {
            return true;
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (mActivity.checkSelfPermission(mCheck.strPermission) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }

        final int thisCallbackCode = callbackNum++;
        IPermissionsHandler mIPermissionsHandler = new IPermissionsHandler() {
            @Override
            public void onPermissionResult(int requestCode, String[] permissions, int[] grantResults) {
                if (requestCode == thisCallbackCode) {
                    boolean granted = PackageManager.PERMISSION_GRANTED == grantResults[0];
                    if (granted) {
                        if (mCheck.nextCheck != null) {
                            PermissionManager.CheckPermissionAtFunc(mActivity, mCheck.nextCheck);
                        } else {
                            mCheck.goFunc();
                        }
                    } else {
                        ShowAlertDialog(mActivity, mCheck.strMsgTip);
                    }
                }
            }
        };
        RequestThePermission(mActivity, mIPermissionsHandler, new String[]{mCheck.strPermission}, thisCallbackCode);
        return false;
    }


}
