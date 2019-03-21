package com.db.sys.dao;

import com.db.common.vo.Node;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMenuDao {
    int deleteObjectsByMenuId(Integer menuId);

    int insertObject(@Param("roleId") Integer roleId,
                     @Param("menuIds") Integer[] menuIds);

    int deleteObjectsByRoleId(Integer roleId);

    List<Integer> findRoleMenuIdsByRoleIds(@Param("roleIds") Integer[] roleIds );

}
