package com.ele.entity;

import java.io.Serializable;

/**
 * Created by yc on 2018/11/27.
 */
public class SysUser extends Common implements Serializable{

    private static final long serialVersionUID = -3449189072171004924L;
    private String userName;
    private String password;
    private String salt;
    private String state;
    private String showName;


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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }
}
