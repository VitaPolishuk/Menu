package com.menuAndersen.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.menuAndersen.dao.*;
import com.menuAndersen.model.*;
import com.menuAndersen.service.*;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan("com.menuAndersen")
@EnableTransactionManagement
public class Config extends WebMvcConfigurerAdapter {
    @Autowired
    private Environment environment;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pages/**").addResourceLocations("/pages/");
    }

    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/pages/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);

        return resolver;
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false));
        return converter;
    }

    @Bean(name = "dataSource")
    public DataSource getdataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/menu?createDatabaseIfNotExist=true");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");
        dataSource.setValidationQuery("select 1");
        return dataSource;
    }

    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {

        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
        sessionBuilder.addProperties(getHibernateProperties());

        sessionBuilder.addAnnotatedClasses(Employees.class);
        sessionBuilder.addAnnotatedClasses(MyDate.class);
        sessionBuilder.addAnnotatedClasses(Complexes.class);
        sessionBuilder.addAnnotatedClasses(Basic.class);
        sessionBuilder.addAnnotatedClasses(DateAndComplexes.class);

        return sessionBuilder.buildSessionFactory();
    }

    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(
            SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(
                sessionFactory);

        return transactionManager;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.id.new_generator_mappings", "false");

        return properties;
    }

    @Autowired
    @Bean(name = "employeesDao")
    public EmployeesDao getEmployeesDao(SessionFactory sessionFactory) {

        return new EmployeesDaoImpl(sessionFactory);
    }

    @Autowired
    @Bean(name = "employeesService")
    public EmployeesService getEmployeesService(EmployeesDao employeesDao) {

        return new EmployeesServiceImpl(employeesDao);
    }

    @Autowired
    @Bean(name = "complexesDao")
    public ComplexesDao getComplexesDao(SessionFactory sessionFactory) {

        return new ComplexesDaoImpl(sessionFactory);
    }

    @Autowired
    @Bean(name = "complexesService")
    public ComplexesService getComplexesService(ComplexesDao complexesDao) {

        return new ComplexesServiceImpl(complexesDao);
    }

    @Autowired
    @Bean(name = "myDateDao")
    public MyDateDao getMyDateDao(SessionFactory sessionFactory) {

        return new MyDateDaoImpl(sessionFactory);
    }

    @Autowired
    @Bean(name = "myDateService")
    public MyDateService gMyDateService(MyDateDao myDateDao) {

        return new MyDateServiceImpl(myDateDao);
    }

    @Autowired
    @Bean(name = "basicDao")
    public BasicDao gBasicDao(SessionFactory sessionFactory) {

        return new BasicDaoImpl(sessionFactory);
    }

    @Autowired
    @Bean(name = "basicService")
    public BasicService gBasicService(BasicDao basicDao) {

        return new BasicServiceImpl(basicDao);
    }
    @Autowired
    @Bean(name = "dateAndComplexesDao")
    public DateAndComplexesDao dateAndComplexesDao(SessionFactory sessionFactory) {

        return new DateAndComplexesDaoImpl(sessionFactory);
    }

    @Autowired
    @Bean(name = "dateAndComplexesService")
    public DateAndComplexesService dateAndComplexesService(DateAndComplexesDao dateAndComplexesDao) {

        return new DateAndComplexesServiceImpl(dateAndComplexesDao);
    }

}
