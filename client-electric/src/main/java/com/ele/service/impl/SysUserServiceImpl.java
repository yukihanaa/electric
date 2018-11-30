package com.ele.service.impl;

import com.ele.entity.SysUser;
import com.ele.mapper.SysUserMapper;
import com.ele.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yc on 2018/11/26.
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;


    @Override
    public List<SysUser> getUserList() throws Exception {
       return sysUserMapper.getUserList();
    }
}
