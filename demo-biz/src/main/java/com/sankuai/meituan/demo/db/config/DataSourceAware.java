package com.sankuai.meituan.demo.db.config;

import java.lang.annotation.*;

/**
 * @author shimanqiang
 * @since 2018/12/29 下午3:04
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSourceAware {
    /**
     * DataSource Name
     *
     * @return
     */
    String value() default "";
}
