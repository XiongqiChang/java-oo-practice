package com.twu.model;

/**
 * @Auther: xqc
 * @Date: 2020/8/28 - 08 - 28 - 10:12
 * @Description: com.twu.model
 * @version: 1.0
 */
public class User {

    private  String userName;
    private  String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(String userName) {
        this.userName = userName;
    }

}
