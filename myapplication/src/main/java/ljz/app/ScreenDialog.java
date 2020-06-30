package ljz.app;

import android.app.Dialog;
import android.content.Context;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import org.jetbrains.annotations.Nullable;


/**
 * 项目名称：LJZ_DEMO
 *
 * @author LJZ ljz@yitong.com.cn
 * Created Time  2019/3/28 10:39.
 * @version 1.0
 */
public class ScreenDialog extends Dialog {

    @RequiresApi(api = Build.VERSION_CODES.P)
    public ScreenDialog(@NonNull Context context) {
        super(context);
//        BiometricPrompt.Builder  builder=  new BiometricPrompt.Builder(context);
//        BiometricPrompt biometricPrompt=builder.build();
//        biometricPrompt.
    }

    public ScreenDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected ScreenDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    private void initView(){



    }
}
