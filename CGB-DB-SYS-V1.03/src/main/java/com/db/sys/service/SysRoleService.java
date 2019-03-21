package com.db.sys.service;

import com.db.common.vo.CheckBox;
import com.db.common.vo.PageObject;
import com.db.common.vo.SysRoleMenuVo;
import com.db.sys.entity.SysRole;

import java.util.List;

public interface SysRoleService {
    PageObject findPageObjects(String name,Integer pageCurrent);
    int deleteObject(Integer id);
    int saveObject(SysRole entity,Integer[] menuIds);
    SysRoleMenuVo findObjectById(Integer id);

    List<CheckBox> findObjects();

}
