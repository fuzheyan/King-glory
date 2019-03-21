package com.db.common.web;

import com.db.common.exception.ServiceException;
import com.db.common.vo.JsonResult;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//全局异常处理类@restcontrollerAdvice
@RestControllerAdvice
public class GlobleExceptionHandler {
    @ExceptionHandler(ShiroException.class)
    public JsonResult doHandlerServiceException(ShiroException e) {
        e.printStackTrace();
        JsonResult r = new JsonResult();
        r.setState(0);
        if (e instanceof UnknownAccountException) {
            r.setMessage("账号密码不存在");
        } else if (e instanceof LockedAccountException) {
            r.setMessage("账号已禁用");
        } else if(e instanceof IncorrectCredentialsException){
            r.setMessage("账号密码不存在");
        }else {//还有其它异常
            r.setMessage(e.getMessage());
        }
        return r;
    }

    //此注解描述的方法为异常处理方法
    @ExceptionHandler(ServiceException.class)
    public JsonResult doHandlerServiceException(ServiceException e) {
        e.printStackTrace();
        return new JsonResult(e);
    }

    @ExceptionHandler(RuntimeException.class)
    public JsonResult doHandlerServiceException(RuntimeException e) {
        e.printStackTrace();
        JsonResult r = new JsonResult();
        r.setMessage("你最美,运行错误");
        r.setState(0);
        return r;
    }
}
