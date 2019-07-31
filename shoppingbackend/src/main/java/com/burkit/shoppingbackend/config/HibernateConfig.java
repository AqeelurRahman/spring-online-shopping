package com.burkit.shoppingbackend.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static org.hibernate.cfg.AvailableSettings.*;
import static org.hibernate.cfg.AvailableSettings.DIALECT;


@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScan(basePackages={"com.burkit.shoppingbackend.dto"})
public class HibernateConfig {
    @Autowired
    private Environment env;

    // Change the below based on the DBMS you choose
    private final static String DATABASE_URL = "jdbc:mysql://localhost:3306/test";
    private final static String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String DATABASE_DIALECT = "MySql5Dialect";
    private final static String DATABASE_USERNAME = "root";
    private final static String DATABASE_PASSWORD = "";

    // dataSource bean will be available
    @Bean("dataSource")
    public DataSource getDataSource() {

        BasicDataSource dataSource = new BasicDataSource();

        // Providing the database connection information
        dataSource.setDriverClassName(DATABASE_DRIVER);
        dataSource.setUrl(DATABASE_URL);
        dataSource.setUsername(DATABASE_USERNAME);
        dataSource.setPassword(DATABASE_PASSWORD);


        return dataSource;

    }

    // sessionFactory bean will be available

//    @Bean
////    public SessionFactory getSessionFactory(DataSource dataSource) {
////
////        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
////
////        builder.addProperties(getHibernateProperties());
////        builder.scanPackages("com.burkit.shoppingbackend.dto");
////
////        return builder.buildSessionFactory();
////
////    }
@Bean
public LocalSessionFactoryBean getSessionFactory() {
    LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

    Properties props = new Properties();
    // Setting JDBC properties
    props.put(DRIVER, env.getProperty("mysql.driver"));
    props.put(URL, env.getProperty("mysql.url"));
    props.put(USER, env.getProperty("mysql.user"));
    props.put(PASS, env.getProperty("mysql.password"));

    // Setting Hibernate properties
    props.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
    props.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));

    // Setting C3P0 properties
    props.put(C3P0_MIN_SIZE, env.getProperty("hibernate.c3p0.min_size"));
    props.put(C3P0_MAX_SIZE, env.getProperty("hibernate.c3p0.max_size"));
    props.put(C3P0_ACQUIRE_INCREMENT,
            env.getProperty("hibernate.c3p0.acquire_increment"));
    props.put(C3P0_TIMEOUT, env.getProperty("hibernate.c3p0.timeout"));
    props.put(C3P0_MAX_STATEMENTS, env.getProperty("hibernate.c3p0.max_statements"));

    props.put(DIALECT, env.getProperty("hibernate.dialect"));
    factoryBean.setHibernateProperties(props);
    factoryBean.setPackagesToScan("com.burkit.shoppingbackend.dto");

    return factoryBean;
}



    // All the hibernate properties will be returned in this method
//    private Properties getHibernateProperties() {
//
//        Properties properties = new Properties();
//
//
//        properties.put("hibernate.dialect", DATABASE_DIALECT);
//        properties.put("hibernate.show_sql", "true");
//        properties.put("hibernate.format_sql", "true");
//
//        //properties.put("hibernate.hbm2ddl.auto", "create");
//
//
//        return properties;
//    }

    // transactionManager bean
//    @Bean
//    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
//        return transactionManager;
//    }

    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }


}
