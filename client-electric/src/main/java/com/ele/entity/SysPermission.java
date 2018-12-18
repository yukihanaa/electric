package com.ele.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by yc on 2018/11/30.
 */
@Data
public class SysPermission extends Common implements Serializable {

    private static final long serialVersionUID = 1808772159184430327L;
    private String url;
    private String permission;
    private String description;

}
