package com.sankuai.meituan.demo.db.config;

/**
 * @author shimanqiang
 * @since 2018/12/29 下午2:49
 */
public final class DataSourceHolder {
    private static final ThreadLocal<String> CURRENT_DATASOURCE_KEY = new ThreadLocal<>();

    /**
     * 选择数据源
     *
     * @param dataSourceKey
     */
    public static void chooseDataSource(String dataSourceKey) {
        CURRENT_DATASOURCE_KEY.set(dataSourceKey);
    }

    /**
     * 使用默认数据源
     */
    public static void chooseDefaultDataSource() {
        CURRENT_DATASOURCE_KEY.remove();
    }

    /**
     * 获取当前数据源
     *
     * @return
     */
    public static String getCurrentDataSource() {
        return CURRENT_DATASOURCE_KEY.get();
    }
}
