package com.db.sys.dao;

import com.db.sys.entity.SysUser;
import com.db.sys.entity.SysUserDeptResult;
import org.apache.ibatis.annotations.Param;
import org.aspectj.lang.annotation.Pointcut;

import java.util.List;

public interface SysUserDao {


    List<SysUserDeptResult> findPageObjects(
            @Param("username") String username,
            @Param("startIndex") Integer startIndex,
            @Param("pageSize") Integer pageSize);

    int getRowCount(@Param("username") String username);

    int validById(@Param("id") Integer id, @Param("valid") Integer valid, @Param("modifiedUser") String modifiedUser);

    int insertObject(SysUser entity);

    SysUser findUserByUserName(String username);

    List<Integer> findRoleIdByUserId(Integer id);

    int updatePassword(
            @Param("username") String username,
            @Param("newpwd") String newpwd,
            @Param("salt") String salt);

}


