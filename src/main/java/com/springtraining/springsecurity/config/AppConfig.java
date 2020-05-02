package com.springtraining.springsecurity.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.logging.Logger;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.springtraining.springsecurity")
@PropertySource("classpath:application.properties")
public class AppConfig implements WebMvcConfigurer {

    // setup env variable
    @Autowired
    private Environment env; // will hold data of property file

    Logger logger = Logger.getLogger(getClass().getName());

    @Bean
    public ViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setViewClass(JstlView.class);
        bean.setPrefix("/WEB-INF/view/");
        bean.setSuffix(".jsp");
        return bean;
    }

    // define bean for security data source
    public DataSource securityDatasource(){
        // create connection pool
        ComboPooledDataSource securityDatasource = new ComboPooledDataSource();

        // set jdbc driver
        try {
            securityDatasource.setDriverClass(env.getProperty("jdbc.driver"));
        } catch (PropertyVetoException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        // log connection props
        logger.info(">>> jdbc.url" + env.getProperty("jdbc.url"));
        logger.info(">>> jdbc.user" + env.getProperty("jdbc.user"));

        // set database connection props
        securityDatasource.setJdbcUrl(env.getProperty("jdbc.url"));
        securityDatasource.setUser(env.getProperty("jdbc.user"));
        securityDatasource.setPassword(env.getProperty("jdbc.password"));

        // set connection pool props
        securityDatasource.setInitialPoolSize(Integer.parseInt("connection.pool.initialPoolSize"));
        securityDatasource.setMinPoolSize(Integer.parseInt("connection.pool.minPoolSize"));
        securityDatasource.setMaxPoolSize(Integer.parseInt("connection.pool.maxPoolSize"));
        securityDatasource.setMaxIdleTime(Integer.parseInt("connection.pool.maxIdleTime"));

        return  securityDatasource;
    }
}
