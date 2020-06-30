package ljz.app.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 项目名称：LJZ_DEMO
 *
 * @author LJZ ljz@yitong.com.cn
 * Created Time  2019/9/27 16:23.
 * @version 1.0
 */
public class User implements Parcelable {

    public User(String userName,String userSex){
        this.userName=userName;
        this.userSex=userSex;

    }

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    private String userSex;


    protected User(Parcel user) {
        userName=user.readString();
        userSex=user.readString();


    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeString(userSex);
    }
}
