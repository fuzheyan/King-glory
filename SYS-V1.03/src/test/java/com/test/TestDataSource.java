package com.test;

import com.mchange.v2.c3p0.jboss.C3P0PooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.mybatis.spring.SqlSessionFactoryBean;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by CGB on 2019/3/7.
 */
public class TestDataSource extends TestBase {
    //测试druid连接池
    @Test
    public void testDruidSource() throws Exception {
        DataSource ds = ctx.getBean("dataSource", DataSource.class);
        System.out.println(ds.getConnection());
    }
    //测试properties文件
    @Test
    public void testProperties(){
        Properties cfg = ctx.getBean("cfg", Properties.class);
        System.out.println(cfg);
    }
    //测试mybatis
    @Test
    public void testSqlSessionFactory(){
        SqlSessionFactory ssf = ctx.getBean("&sqlSessionFactory", SqlSessionFactory.class);
        System.out.println(ssf);
    }
    //测试c3p0连接池
    @Test
    public void testC3P0() throws SQLException {
        DataSource c3p0 = ctx.getBean("c3P0",DataSource.class);
        System.out.println(c3p0.getConnection());
    }
}