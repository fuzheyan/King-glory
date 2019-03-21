package com.db.sys.controller;

import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by CGB on 2019/3/7.
 */
@Controller
public class PageController {
    @RequestMapping("doLoginUI")
    public String doLoginUI(){
        return "login";
    }
    //方法调用
    //1.请求的url会交给DspatchServlet对象
    //2.DispatchServlet对象基于请求URL从HandlerMapping中获取handlermethod
    //3.DispatchServlet对象基于反射调用controller

    @RequestMapping("/")
    public String page(@PathVariable String page){
        return "starter";
    }
    @RequestMapping("doIndexUI")
    public String doIndexUI(){
        return "starter";
    }
    //基于其方法返回分页页面@return
    @RequestMapping("doPageUI")
    public String doPageUI(){
        return "common/page";
    }

}
