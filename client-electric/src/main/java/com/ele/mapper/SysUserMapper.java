package com.ele.mapper;

import com.ele.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yc on 2018/11/27.
 */
@Repository
@Mapper
public interface SysUserMapper {

    List<SysUser> getUserList() throws Exception;
}
