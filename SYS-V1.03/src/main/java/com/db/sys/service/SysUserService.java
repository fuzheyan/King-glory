package com.db.sys.service;

import com.db.common.vo.PageObject;
import com.db.sys.entity.SysUser;
import com.db.sys.entity.SysUserDeptResult;

public interface SysUserService {
    PageObject<SysUserDeptResult> findPageObjects(
            String username,
            Integer pageCurrent);

    int validById(Integer id,Integer valid,String modified);

    int saveObject(SysUser entity,Integer[] roleIds);

    int updatePassword(String newpwd,String oldpwd,String cgfpwd);

}
