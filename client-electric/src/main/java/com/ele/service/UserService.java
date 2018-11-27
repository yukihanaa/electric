package com.ele.service;

import com.ele.entity.EO.User;

import java.util.List;

/**
 * Created by yc on 2018/11/26.
 */

public interface UserService {

    List<User> getUserList() throws Exception;
}
