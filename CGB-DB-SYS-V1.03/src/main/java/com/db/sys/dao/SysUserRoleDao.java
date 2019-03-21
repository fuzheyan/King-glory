package com.db.sys.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserRoleDao {
    int insertObjects(
            @Param("userId") Integer userId,
            @Param("roleIds") Integer[] roleIds);

    List<Integer>findMenuIdByRoleIds(Integer id);

}
