package com.sankuai.meituan.demo.db.config;

import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author shimanqiang
 * @since 2018/12/29 下午3:36
 */
@Configuration
public class DataSourceAutoDiscovery implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAssignableFrom(MapperFactoryBean.class)) {
            MapperFactoryBean mapperFactoryBean = (MapperFactoryBean) bean;
            Class mapperInterface = mapperFactoryBean.getMapperInterface();
            System.out.println(mapperInterface);
            DataSourceAware anno = (DataSourceAware) mapperInterface.getAnnotation(DataSourceAware.class);
            if (anno != null) {
                return new MapperProxyFactoryBean(mapperFactoryBean);
            }

        }
        return bean;
    }

    private static class MapperProxyFactoryBean implements FactoryBean {
        private FactoryBean mapperFactoryBeanObject;
        private Object originalObject;
        private ClassLoader classLoader;
        private Class mapperInterface;
        private String dataSourceKey;

        public MapperProxyFactoryBean(FactoryBean mapperFactoryBeanObject) {
            try {
                this.mapperFactoryBeanObject = mapperFactoryBeanObject;
                this.originalObject = mapperFactoryBeanObject.getObject();
                this.classLoader = mapperFactoryBeanObject.getClass().getClassLoader();
                this.mapperInterface = mapperFactoryBeanObject.getObjectType();
                DataSourceAware anno = (DataSourceAware) mapperInterface.getAnnotation(DataSourceAware.class);
                this.dataSourceKey = anno.value();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public Object getObject() throws Exception {
            return Proxy.newProxyInstance(classLoader,
                    new Class[]{mapperInterface},
                    new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            if (Object.class.equals(method.getDeclaringClass())) {
                                return method.invoke(originalObject, args);
                            }
                            DataSourceHolder.chooseDataSource(dataSourceKey);
                            Object ret = method.invoke(originalObject, args);
                            DataSourceHolder.chooseDefaultDataSource();
                            return ret;
                        }
                    });
        }

        @Nullable
        @Override
        public Class<?> getObjectType() {
            return mapperInterface;
        }

        @Override
        public boolean isSingleton() {
            return true;
        }
    }
}
