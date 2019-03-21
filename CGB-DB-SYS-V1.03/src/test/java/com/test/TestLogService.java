package com.test;

import com.db.common.vo.PageObject;
import com.db.sys.entity.SysLog;
import com.db.sys.service.SysLogService;
import org.junit.Test;

public class TestLogService extends TestBase {
    @Test
    public void testFindPageObject() {
        SysLogService logService = ctx.getBean("sysLogServiceImpl", SysLogService.class);
        PageObject<SysLog> po = logService.findPageObject("admin", 1);
        System.out.println(po.getRowCount());
    }
}
