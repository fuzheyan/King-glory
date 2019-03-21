package com.db.common.aspect;

import com.alibaba.druid.util.StringUtils;
import com.db.common.annotation.RequiredLog;
import com.db.common.utils.IPUtils;
import com.db.sys.dao.SysLogDao;
import com.db.sys.entity.SysLog;
import com.db.sys.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.net.util.IPAddressUtil;
import sun.security.x509.IPAddressName;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@Aspect
@Service
public class SysLogAspect {
    @Autowired
    private SysLogDao sysLogDao;

    /**
     * 定义切入点表达式:(切入点可以理解为切入扩展功能的点)
     * 1)bean(表达式):表达式一般为一个bean的名字,例如
     * a)bean(sysUserServiceImpl)
     * b)bean(*ServiceImpl)
     * 2)@annotation(表达式):注解方式的切入点表达式
     * a)@annotation(com.db.common.annotation.RequiredLog)
     */
    //@Pointcut("bean(sysUserServiceImpl)")
    @Pointcut("@annotation(com.db.common.annotation.RequiredLog)")
    public void logPointCut() {
    }

    /**
     * 定义切入表达式
     * 1）bean（"***"）粗粒度的表达式
     */
    @Around("logPointCut()")
    public Object aroundMethod(ProceedingJoinPoint jp)
            throws Throwable{
        long t1=System.currentTimeMillis();
        //执行目标方法
        Object result=jp.proceed();
        long t2=System.currentTimeMillis();
        System.out.println("execute time "+(t2-t1));
        //获取日志信息,并将其写入到数据库
        saveLog(jp,(t2-t1));
        return result;
    }
    private void saveLog(ProceedingJoinPoint jp, long time) throws NoSuchMethodException {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        String username = user.getUsername();
        Object target = jp.getTarget();
        String targetClsName = target.getClass().getName();
        MethodSignature ms = (MethodSignature) jp.getSignature();
        String methodName = ms.getName();

        String method = targetClsName + "." + methodName;

        String params = Arrays.toString(jp.getArgs());
        //先获取目标对象
        Method targetMethod = target.getClass().getDeclaredMethod(methodName,ms.getParameterTypes());
        //获取目标方法的注解
        RequiredLog requiredLog= targetMethod.getDeclaredAnnotation(RequiredLog.class);
        //获取注解操作名称
        String operation = methodName;

        if (requiredLog!=null&&!StringUtils.isEmpty(requiredLog.value())){
            operation=requiredLog.value();
        }

        String ip = IPUtils.getIpAddr();


        //封装日志
        SysLog log = new SysLog();
        log.setUsername(username);
        log.setMethod(method);
        log.setParams(params);
        log.setOperation(operation);
        log.setIp(ip);
        log.setTime(time);
        log.setCreatedTime(new Date());
        //3.将日志写入到数据库
        sysLogDao.insertObjct(log);
    }
}
