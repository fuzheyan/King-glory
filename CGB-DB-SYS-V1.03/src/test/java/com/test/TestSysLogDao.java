package com.test;

import com.db.sys.dao.SysLogDao;
import com.db.sys.entity.SysLog;
import org.junit.Test;
import org.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *spring/spring-datasource.xml
 */
public class TestSysLogDao extends TestBase{
    @Test
    public void testFindById() throws SQLException, ClassNotFoundException {
        SysLogDao bean = ctx.getBean("sysLogDao", SysLogDao.class);
        Map<String, Object> map = bean.findById(9);
        System.out.println(map);


    }

    @Test
    public void testfindPageObject(){
        SysLogDao bean1 = ctx.getBean("sysLogDao",SysLogDao.class);

        List<SysLog> logs = bean1.findPageObject("username", 3, 3);
        System.out.println(logs);
    }

}
