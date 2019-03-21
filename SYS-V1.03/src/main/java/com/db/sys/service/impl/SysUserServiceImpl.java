package com.db.sys.service.impl;

import com.db.common.annotation.RequiredLog;
import com.db.common.exception.ServiceException;
import com.db.common.vo.PageObject;
import com.db.sys.dao.SysUserDao;
import com.db.sys.dao.SysUserRoleDao;
import com.db.sys.entity.SysUser;
import com.db.sys.entity.SysUserDeptResult;
import com.db.sys.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserDao sysUserDao;
    @Resource
    private SysUserRoleDao sysUserRoleDao;

    @Override
    public PageObject<SysUserDeptResult> findPageObjects(String username,
                                                         Integer pageCurrent) {
        //1.数据合法性验证
        if (pageCurrent == null || pageCurrent <= 0)
            throw new ServiceException("参数不合法");
//2.依据条件获取总记录数
        int rowCount = sysUserDao.getRowCount(username);
        if (rowCount == 0)
            throw new ServiceException("记录不存在");
        //3.计算startIndex的值
        int pageSize = 3;
        int startIndex = (pageCurrent - 1) * pageSize;
        //4.依据条件获取当前页数据
        List<SysUserDeptResult> records =
                sysUserDao.findPageObjects(
                        username, startIndex, pageSize);
        //5.封装数据
        PageObject<SysUserDeptResult> pageObject = new PageObject<>();
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setRowCount(rowCount);
        pageObject.setPageSize(pageSize);
        pageObject.setRecords(records);
        return pageObject;
    }
    /**
     * 执行禁用启用操作
     * @RequiresPermissions 注解修饰的方法表示
     * 此方法必须授权访问.注解内部的字符串表示权限
     * 标识(用户拥有此权限才可以访问资源.)
     */
    @RequiresPermissions("sys:user:valid")
    @RequiredLog("禁用启用")
    @Override
    public int validById(Integer id, Integer valid, String modified) {
        if (id == null || id <= 0) throw new ServiceException("参数不合法" + id);
        if (valid != 1 && id <= 0) throw new ServiceException("参数不合法" + valid);
        if (StringUtils.isEmpty(modified)) throw new ServiceException("修改用户不能为空");
        //执行禁用启用操作
        int rows = 0;
        try {
            rows = sysUserDao.validById(id, valid, modified);
        } catch (Throwable e) {
            e.printStackTrace();

            throw new ServiceException(" 服务器正在维护");
        }
        if (rows == 0) throw new ServiceException("记录不存在");
        return rows;

    }

    @Override
    public int saveObject(SysUser entity, Integer[] roleIds) {
        if (entity == null)
            throw new ServiceException("保存对象不能为空");
        if (StringUtils.isEmpty(entity.getUsername()))
            throw new ServiceException("用户名不能为空");
        if (StringUtils.isEmpty(entity.getPassword()))
            throw new ServiceException("密码不能为空");
        if (roleIds == null || roleIds.length == 0)
            throw new ServiceException("至少要为用户分配角色");

        //2.将数据写入数据库
        String salt = UUID.randomUUID().toString();
        entity.setSalt(salt);
        //加密(先了解,讲shiro时再说)
        SimpleHash sHash =
                new SimpleHash("MD5", entity.getPassword(), salt);
        entity.setPassword(sHash.toHex());

        int rows = sysUserDao.insertObject(entity);
        sysUserRoleDao.insertObjects(
                entity.getId(),
                roleIds);//"1,2,3,4";
        //3.返回结果
        return rows;

    }

    @Override
    public int updatePassword(String newpwd, String oldpwd, String cfgpwd) {
        //校验字符串
        if (StringUtils.isEmpty(newpwd)) throw new ServiceException("新密码不能为空");
        if (StringUtils.isEmpty(oldpwd)) throw new ServiceException("旧密码不能为空");
        if (StringUtils.isEmpty(cfgpwd)) throw new ServiceException("确认密码不能为空");
        if (!newpwd.equals(cfgpwd)) throw new ServiceException("两次输入的新密码不一致");
        //密码校验
        String regStr = "^([A-Z]|[a-z]|[0-9]|[`-=[];,./~!@#$%^*()_+}{:?]){6,20}$";

//        if (regStr.newpwd) throw new ServiceException("密码长度不能小于6");

        //获取用户信息
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        if (sysUser == null) throw new ServiceException("请登录用户");
        //获取用户原始密码
        String sourcePwd = sysUser.getPassword();
        System.out.println(sourcePwd);
        System.out.println(oldpwd);
        SimpleHash simpleHash = new SimpleHash("MD5", oldpwd, sysUser.getSalt(), 1);
        String newHax = simpleHash.toHex();
        System.out.println(newHax);
        if (!sourcePwd.equals(newHax)) throw new ServiceException("密码不正确");
        //更新
        String newSalt = UUID.randomUUID().toString();
        simpleHash = new SimpleHash("MD5", newpwd, newSalt, 1);
        int rows = sysUserDao.updatePassword(
                sysUser.getUsername(),
                simpleHash.toHex(),
                newSalt);
        if (rows == 0)
            throw new ServiceException("记录可能已经不存在");
        return rows;

    }


}
