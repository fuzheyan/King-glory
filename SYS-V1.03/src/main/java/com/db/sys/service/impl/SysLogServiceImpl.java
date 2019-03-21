package com.db.sys.service.impl;

import com.db.common.annotation.RequiredCache;
import com.db.common.exception.ServiceException;
import com.db.common.utils.PageUtils;
import com.db.common.vo.PageObject;
import com.db.sys.dao.SysLogDao;
import com.db.sys.entity.SysLog;
import com.db.sys.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public PageObject<SysLog> findPageObject(String name, Integer pageCurrent) {
        if (pageCurrent == null || pageCurrent < 1)
            throw new IllegalArgumentException("页码不正确");
        int rowCount = sysLogDao.getRowCount(name);
        //2.基于条件查询总记录数
        //2.1) 执行查询
        if (rowCount == 0)
            throw new ServiceException("系统没有查到对应记录");
        //3.基于条件查询当前页记录(pageSize定义为2)
        //3.1)定义pageSize
        int pageSize = 3;
        //3.2)计算startIndex
        int startIndex = (pageCurrent - 1) * pageSize;
        //3.3)执行当前数据的查询操作
        List<SysLog> records =
                sysLogDao.findPageObject(name, startIndex, pageSize);
        //4.对分页信息以及当前页记录进行封装
        //4.1)构建PageObject对象
        PageObject<SysLog> po = PageUtils.newPageObject(pageCurrent, rowCount, pageSize, records);
        return po;
    }

    @Transactional
    @Override
    public int deleteObjects(Integer... ids) {
        //1.判定参数合法性
        if (ids == null || ids.length == 0)
            throw new ServiceException("请选择一个");
        //2.执行删除操作
        int rows;
        try {
            rows = sysLogDao.deleteObject(ids);
        } catch (Throwable e) {
            e.printStackTrace();
            //发出报警信息(例如给运维人员发短信)
            throw new ServiceException("系统故障，正在恢复中...");
        }
        //4.对结果进行验证
        if (rows == 0)
            throw new ServiceException("记录可能已经不存在");
        //5.返回结果
        return rows;
    }
}




