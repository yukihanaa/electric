package com.ele.entity;

import java.io.Serializable;

/**
 * Created by yc on 2018/11/30.
 */
public class SysRole extends Common implements Serializable {

    private static final long serialVersionUID = 5946607701479009708L;
    private String roleNo;
    private String roleName;
    private String description;
    //是否可用；1是，0否
    private Integer avaliable;

    public String getRoleNo() {
        return roleNo;
    }

    public void setRoleNo(String roleNo) {
        this.roleNo = roleNo;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAvaliable() {
        return avaliable;
    }

    public void setAvaliable(Integer avaliable) {
        this.avaliable = avaliable;
    }
}
