package com.db.common.aspect;

import com.db.common.utils.LruCache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

@Service
@Aspect
public class CacheAspect {
    private LruCache<CacheKey, Object> cache = new LruCache<>(5);

    @Around("@annotation(com.db.common.annotation.RequiredCache)")
    public Object around(ProceedingJoinPoint jp) throws Throwable {

        CacheKey key = createCacheKey(jp);
        //1.从缓存获取中取数据,假如缓存中有则直接返回
        Object obj = cache.get(key);
        if (obj != null) return obj;
        //3.缓存没有数据,则直接目标方法.
        obj = jp.proceed();
        //4.将目标方法的执行结果存储到缓存对象.
        cache.put(key, obj);
        //5.返回结果
        return obj;
    }

    private CacheKey createCacheKey(ProceedingJoinPoint jp) {
        Class<?> targetClass = jp.getTarget().getClass();
        MethodSignature ms =
                (MethodSignature) jp.getSignature();
        Method targetMethod = ms.getMethod();
        Object[] args = jp.getArgs();
        CacheKey key = new CacheKey(targetClass, targetMethod, args);
        return key;
    }

}
