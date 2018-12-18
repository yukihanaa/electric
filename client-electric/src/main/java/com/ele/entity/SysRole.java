package com.ele.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by yc on 2018/11/30.
 */
@Data
public class SysRole extends Common implements Serializable {

    private static final long serialVersionUID = 5946607701479009708L;
    private String roleNo;
    private String roleName;
    private String description;
    //是否可用；1是，0否
    private Integer avaliable;
}
