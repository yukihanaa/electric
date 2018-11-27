package com.ele.service.impl;

import com.ele.entity.EO.User;
import com.ele.mapper.UserMapper;
import com.ele.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yc on 2018/11/26.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> getUserList() throws Exception {
       return userMapper.getUserList();
    }
}
