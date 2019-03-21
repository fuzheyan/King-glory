package com.db.sys.dao;

import com.db.common.vo.CheckBox;
import com.db.common.vo.SysRoleMenuVo;
import com.db.sys.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleDao {
    List<SysRole> findPageObjects(
            @Param("name") String name,
            @Param("startIndex") Integer startIndex,
            @Param("pageSize") Integer pageSize);

    int getRowCount(String name);

    int deleteObject(Integer id);

    int insertObject(SysRole entity);

    int updateObject(SysRole entuty);

    SysRoleMenuVo findObjectById(Integer id);

    List<CheckBox> findObjects();

}
