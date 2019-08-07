package com.mc.redis.configuration.datasource; /**
 * All rights Reserved, Designed By MiGu
 * Copyright: Copyright(C) 2016-2020
 * Company MiGu Co., Ltd.
 */

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 项目名称: campaign-service
 * 包: com.migu.pinkstone.datasource
 * 类名称: BusinessDataSourceConfig.java
 * 类描述: 业务库的数据源配置 ，根据包路径找对应的数据源
 * 创建人: liyin
 * 创建时间: 2017年12月11日 下午15:02:01
 */
@Configuration
@MapperScan(basePackages = BusinessDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "businessSqlSessionFactory")
public class BusinessDataSourceConfig {

    /**
     * PACKAGE.
     */
    static final String PACKAGE = "com.mc.redis.configuration.mapper";
    /**
     * MAPPER_LOCATION.
     */
    static final String MAPPER_LOCATION = "classpath:sql/*Mapper.xml";
    /**
     * 数据库url.
     */
    @Value("${spring.userBusinessDatasource.url}")
    private String url;
    /**
     * 数据库用户名.
     */
    @Value("${spring.userBusinessDatasource.username}")
    private String user;
    /**
     * 数据库用户密码.
     */
    @Value("${spring.userBusinessDatasource.password}")
    private String password;
    /**
     * 数据库驱动.
     */
    @Value("${spring.userBusinessDatasource.driver-class-name}")
    private String driverClass;

    /**
     * @return dataSource
     */
    @Bean(name = "businessDataSource")
    public DataSource businessDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setInitialSize(5);
        dataSource.setMinIdle(5);
        dataSource.setMaxWait(60000);
        dataSource.setValidationQuery("select 1");
        dataSource.setMaxActive(20);
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setRemoveAbandoned(true);
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        dataSource.setMinEvictableIdleTimeMillis(300000);
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        String connectionInitSqls = "SET NAMES utf8mb4";
        StringTokenizer tokenizer = new StringTokenizer(connectionInitSqls, ";");
        dataSource.setConnectionInitSqls(Collections.list(tokenizer));//重点设置该参数
        return dataSource;
    }

    /**
     * @return DataSourceTransactionManager
     */
    @Bean
    @Primary
    public DataSourceTransactionManager businessTransactionManager() {
        return new DataSourceTransactionManager(businessDataSource());
    }

    /**
     * @return getObject
     * @throws Exception
     */
    @Bean
    @Primary
    public SqlSessionFactory businessSqlSessionFactory() throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(businessDataSource());
        sessionFactory.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(BusinessDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

    /**
     * @return JdbcTemplate
     */
    @Bean
    @Primary
    public JdbcOperations businessJdbcTemplate() {
        return new JdbcTemplate(businessDataSource());
    }
}
