package com.sankuai.meituan.demo.db.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author shimanqiang
 * @since 2018/12/29 下午2:59
 */
@Component
@Aspect
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

    @Before("@annotation(dataSourceAware)")
    public void before( DataSourceAware dataSourceAware) {


        System.out.println("-----------------------");
        System.out.println(dataSourceAware.value());
        DataSourceHolder.chooseDataSource(dataSourceAware.value());
    }

    @After("@annotation(dataSourceAware)")
    public void after(JoinPoint point, DataSourceAware dataSourceAware) {
        DataSourceHolder.chooseDefaultDataSource();
    }
}
