package ljz.app.entity;

import java.io.Serializable;

/**
 * 项目名称：LJZ_DEMO
 *
 * @author LJZ
 * Created Time  2019/9/29 10:46.
 * @version 1.0
 */
public class UserInfor implements Serializable {
    private static final  long serialVersionUID=1L;
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

    private String userName;
    private String userSex;

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    private int userAge;

}
