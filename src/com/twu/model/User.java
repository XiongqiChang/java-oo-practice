package com.twu.model;

/**
 * @author r: xqc
 * @Date: 2020/8/28 - 08 - 28 - 10:12
 * @Description: com.twu.model
 * @version: 1.0
 */
public class User {

    private  String userName;
    private  String password;

    private Integer voteCount;

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

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

    public User(String userName, Integer voteCount) {
        this.userName = userName;
        this.voteCount = voteCount;
    }
}
