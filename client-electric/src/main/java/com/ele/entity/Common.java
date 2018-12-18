package com.ele.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by yc on 2018/11/30.
 */
@Data
public class Common implements Serializable{

    private static final long serialVersionUID = -5148645974018795193L;

    private Integer id;

    private String createTime;
    private Integer createBy;
    private String updateTime;
    private Integer updateBy;
}
