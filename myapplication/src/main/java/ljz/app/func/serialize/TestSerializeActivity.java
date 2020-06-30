package ljz.app.func.serialize;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import ljz.app.R;
import ljz.app.entity.User;
import ljz.app.entity.UserInfor;
import ljz.app.func.utils.FileUtil;

/**
 * 项目名称：LJZ_DEMO
 *
 * @author LJZ
 * Created Time  2019/9/29 10:48.
 * @version 1.0
 */
public class TestSerializeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serialize);
    }


    public void testSerializable(View v) throws IOException {
        UserInfor userInfor = new UserInfor();
        userInfor.setUserName("LJZ");
        userInfor.setUserSex("男");
        String path = FileUtil.SDCARD+"userInfor.txt";
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(new File(path)));
        outputStream.writeObject(userInfor);
        System.out.println("序列化成功");
        outputStream.close();
    }

    public void testDeSerializable(View v) throws IOException, ClassNotFoundException {
        String path = FileUtil.SDCARD+"userInfor.txt";
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File(path)));
        UserInfor userInfor = (UserInfor) objectInputStream.readObject();
        System.out.println("反序列化成功");
        objectInputStream.close();
        if (null != userInfor) {
            System.out.println("userinfor Name " + userInfor.getUserName());
        }


    }

    public void testParcelable(View v) {
        User user=new User("LJZ","男");

        Bundle bundle = new Bundle();
//        bundle.putParcelable("USER",user);

        Intent intent=new Intent();
        intent.putExtra("USER",user);
        intent.setClass(this, ParcelActivity.class);
        startActivity(intent);
    }
}
