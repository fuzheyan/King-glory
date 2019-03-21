package com.db.common.aspect;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

public class CacheKey implements Serializable {

    private static final long serialVersionUID = 691438123435201180L;
    private Class <?>targetClass;
    private Method targetMethod;
    private Object[] args;

    public CacheKey(Class<?> targetClass, Method targetMethod, Object[] args) {
        this.targetClass = targetClass;
        this.targetMethod = targetMethod;
        this.args = args;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    public Method getTargetMethod() {
        return targetMethod;
    }

    public void setTargetMethod(Method targetMethod) {
        this.targetMethod = targetMethod;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CacheKey cacheKey = (CacheKey) o;
        return Objects.equals(targetClass, cacheKey.targetClass) &&
                Objects.equals(targetMethod, cacheKey.targetMethod) &&
                Arrays.equals(args, cacheKey.args);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(targetClass, targetMethod);
        result = 31 * result + Arrays.hashCode(args);
        return result;
    }
}
