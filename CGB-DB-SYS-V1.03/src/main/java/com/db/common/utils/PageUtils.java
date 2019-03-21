package com.db.common.utils;

import com.db.common.vo.PageObject;
import com.db.sys.entity.SysLog;

import java.util.List;

public class PageUtils {
    public static <T>PageObject<T> newPageObject(Integer pageCurrent, int rowCount, int pageSize, List<T> records) {
        PageObject<T> pageObject = new PageObject<>();
        //4.2)封装数据
        pageObject.setPageCurrent(pageCurrent);
        pageObject.setPageSize(pageSize);
        pageObject.setRowCount(rowCount);
        pageObject.setRecords(records);
        pageObject.setPageCount((rowCount - 1) / pageSize + 1);
        //5.返回封装结果。
        return pageObject;
    }
}
