package ljz.app.func.serialize;

import android.content.Intent;
import android.os.Bundle;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import ljz.app.R;
import ljz.app.entity.User;

/**
 * 项目名称：LJZ_DEMO
 *
 * @author LJZ
 * Created Time  2019/9/29 13:52.
 * @version 1.0
 */
public class ParcelActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcel);
        TextView textView = findViewById(R.id.tv_receiver);
        Intent intent = getIntent();
        if (null != intent) {
            User user = intent.getParcelableExtra("USER");
            if (null != user) {
                textView.setText(user.getUserName());

                Toast.makeText(this, "传过来的用户姓名：" + user.getUserName() + " 性别：" + user.getUserSex(), Toast.LENGTH_LONG).show();

            }


        }


    }
}