package com.aston.intensive.hibernate.astonintensivehibernate.config;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@RequiredArgsConstructor
@EnableTransactionManagement
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class HibernateConfiguration {
    private static final String PACKAGES_TO_SCAN = "com.aston.intensive.hibernate.astonintensivehibernate.model";

    DataSource dataSource;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        var localSessionFactoryBean = new LocalSessionFactoryBean();

        localSessionFactoryBean.setDataSource(dataSource);
        localSessionFactoryBean.setPackagesToScan(PACKAGES_TO_SCAN);
        localSessionFactoryBean.setHibernateProperties(hibernateProperties());

        return localSessionFactoryBean;
    }

    @Bean
    public Properties hibernateProperties() {
        var properties = new Properties();

        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");

        return properties;
    }
}
