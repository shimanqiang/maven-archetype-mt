package com.sankuai.meituan.demo.db.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 多数据源实现
 *
 * @author shimanqiang
 * @since 2018/12/29 下午2:39
 */
public class MultiDataSource extends AbstractRoutingDataSource {
    public MultiDataSource() {
        //严格使用配置的数据源
        setLenientFallback(false);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceHolder.getCurrentDataSource();
    }
}
