package com.ele.mapper;

import com.ele.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yc on 2018/11/27.
 */
@Repository
@Mapper
public interface SysRoleMapper {

    List<SysRole> getRoleList() throws Exception;
}
