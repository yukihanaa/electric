package com.ele.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by yc on 2018/11/27.
 */
@Data
public class SysUser extends Common implements Serializable{

    private static final long serialVersionUID = -3449189072171004924L;
    private String userName;
    private String password;
    private String salt;
    private String state;
    private String showName;
}
