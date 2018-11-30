package com.ele.service;

import com.ele.entity.SysUser;

import java.util.List;

/**
 * Created by yc on 2018/11/26.
 */

public interface SysUserService {

    List<SysUser> getUserList() throws Exception;
}
