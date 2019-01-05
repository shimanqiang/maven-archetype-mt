package com.sankuai.meituan.demo.db.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * @author shimanqiang
 * @since 2018/12/29 下午2:59
 */
//@Component
//@Aspect
//@EnableAspectJAutoProxy(proxyTargetClass = false)
public class DataSourceAop {
//    @Around("execution(* com.sankuai.meituan.demo..mapper..*.*(..))")
//    public Object doAround(ProceedingJoinPoint jp) throws Throwable {
//        System.out.println(jp.getTarget().getClass());
//        System.out.println(jp.getThis().getClass());
//        System.out.println(jp.getSignature());
//        System.out.println(jp.getTarget().getClass().getSimpleName());
//        System.out.println(jp.getKind());
//        DataSourceAware annotation = jp.getTarget().getClass().getAnnotation(DataSourceAware.class);
//        System.out.println("2222222222");
//        if (annotation != null) {
//            DataSourceHolder.chooseDataSource(annotation.value());
//        }
//        return jp.proceed();
//    }

//    @Around("@target(DataSourceAware)")
//    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
//        System.out.println(joinPoint.getTarget().getClass());
//        System.out.println(joinPoint.getThis().getClass());
//        System.out.println(joinPoint.getSignature());
//        System.out.println(joinPoint.getTarget().getClass().getSimpleName());
//        System.out.println(joinPoint.getKind());
//        DataSourceAware annotation = joinPoint.getTarget().getClass().getAnnotation(DataSourceAware.class);
//        if (annotation != null) {
//            DataSourceHolder.chooseDataSource(annotation.value());
//        }
//        return joinPoint.proceed();
//    }

    @Around("execution(* com.sankuai.meituan.demo..mapper..*.*(..)) && @target(DataSourceAware)")
    public Object doAround(ProceedingJoinPoint joinPoint){
        try {
            System.out.println(joinPoint.getTarget().getClass());
            System.out.println(joinPoint.getThis().getClass());
            System.out.println(joinPoint.getSignature());
            System.out.println(joinPoint.getTarget().getClass().getSimpleName());
            System.out.println(joinPoint.getKind());
            DataSourceAware annotation = joinPoint.getTarget().getClass().getAnnotation(DataSourceAware.class);
            if (annotation != null) {
                DataSourceHolder.chooseDataSource(annotation.value());
            }
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            return null;
        }
    }



//    @Before("@annotation(dataSourceAware)")
//    public void before(DataSourceAware dataSourceAware) {
//
//
//        System.out.println("-----------------------");
//        System.out.println(dataSourceAware.value());
//        DataSourceHolder.chooseDataSource(dataSourceAware.value());
//    }
//
//    @After("@annotation(dataSourceAware)")
//    public void after(JoinPoint point, DataSourceAware dataSourceAware) {
//        DataSourceHolder.chooseDefaultDataSource();
//    }
}
