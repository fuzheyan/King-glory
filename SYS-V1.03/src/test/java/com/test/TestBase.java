package com.test;

import org.junit.After;
import org.junit.Before;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by CGB on 2019/3/7.
 */
public class TestBase {
    protected static ClassPathXmlApplicationContext ctx=null;
    @Before
    public void init(){
        ctx=new ClassPathXmlApplicationContext("spring/spring-config.xml");
    }
    @After
    public void destory(){
        ctx.close();
    }
}
