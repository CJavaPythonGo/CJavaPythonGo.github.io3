package com.lovo.sgproj.bean;

//登陆用户实体
public class UserBean {

    private int userID;

    private String username; //用户名

    private String password;//密码

    public UserBean() {
    }

    public UserBean(int userID, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;
    }

    public UserBean(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
