package com.tm.tool.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;


@Configuration
public class DBConfig {

	@Autowired
	Environment env;

	@Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();        
        dataSource.setDriverClassName(env.getProperty("db.auth.driver"));
        dataSource.setUrl(env.getProperty("db.auth.url"));
        dataSource.setUsername(env.getProperty("db.auth.username"));
        dataSource.setPassword(env.getProperty("db.auth.password"));
        dataSource.setInitialSize(2);
        dataSource.setMaxActive(5);
        dataSource.setMinIdle(2);
        dataSource.setMaxIdle(5);
        dataSource.setMaxWait(10000);
        dataSource.setValidationQuery("SELECT 1");
        return dataSource;
    }



}
