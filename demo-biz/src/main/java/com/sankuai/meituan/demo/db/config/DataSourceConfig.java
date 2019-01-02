package com.sankuai.meituan.demo.db.config;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shimanqiang
 * @since 2018/12/29 下午3:50
 */
@Configuration
@MapperScan({"com.sankuai.meituan.demo.db.mapper"})
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        MultiDataSource multiDataSource = new MultiDataSource();
        multiDataSource.setDefaultTargetDataSource(DataSourceCreator.dataSourceTest);

        Map<Object, Object> targetDataSourceMap = new HashMap<>();
        targetDataSourceMap.put("dataSourceTest", DataSourceCreator.dataSourceTest);
        targetDataSourceMap.put("dataSourceOrder", DataSourceCreator.dataSourceOrder);
        multiDataSource.setTargetDataSources(targetDataSourceMap);
        return multiDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) {
        try {
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(dataSource);


//            String ALIASES_PACKAGE = "com.sankuai.meituan.demo.db.entitys";
//            String MAPPER_LOCATIONS = "classpath:sqlMaps/**/*.xml"; //**表示迭代查找
//            String MYBATIS_CONFIGLOCATION = "classpath:mybatis-config.xml";
//
//            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//            sqlSessionFactoryBean.setTypeAliasesPackage(ALIASES_PACKAGE);
//            sqlSessionFactoryBean.setMapperLocations(resolver.getResources(MAPPER_LOCATIONS));
//            sqlSessionFactoryBean.setConfigLocation(resolver.getResource(MYBATIS_CONFIGLOCATION));

            return sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            return null;
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * 配置事务管理
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    //@Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.sankuai.meituan.demo.db.mapper");
        mapperScannerConfigurer.setAnnotationClass(Mapper.class);
        return mapperScannerConfigurer;
    }

    private static final class DataSourceCreator {

        public static final DataSource dataSourceTest = DataSourceBuilder.create()
                //.driverClassName("com.mysql.jdbc.Driver")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .type(com.alibaba.druid.pool.DruidDataSource.class)
                .url("jdbc:mysql://localhost:3306/my_test?useSSL=false&characterEncoding=utf8")
                .username("root")
                .password("123456")
                .build();


        public static final DataSource dataSourceOrder = DataSourceBuilder.create()
                //.driverClassName("com.mysql.jdbc.Driver")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .type(com.alibaba.druid.pool.DruidDataSource.class)
                .url("jdbc:mysql://localhost:3306/my_order?useSSL=false&characterEncoding=utf8")
                .username("root")
                .password("123456")
                .build();
    }
}
