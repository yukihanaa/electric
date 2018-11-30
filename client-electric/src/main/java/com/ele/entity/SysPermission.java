package com.ele.entity;

import java.io.Serializable;

/**
 * Created by yc on 2018/11/30.
 */
public class SysPermission extends Common implements Serializable {

    private static final long serialVersionUID = 1808772159184430327L;
    private String url;
    private String permission;
    private String description;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
