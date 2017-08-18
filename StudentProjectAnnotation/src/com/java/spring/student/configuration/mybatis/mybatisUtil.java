package com.java.spring.student.configuration.mybatis;


import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.java.spring.student.mapper")
public class mybatisUtil {
	
	@Bean (name = "dataSource")
	public DataSource dataSource(){
	       DriverManagerDataSource dataSource = new DriverManagerDataSource();
	       dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	       dataSource.setUrl("jdbc:mysql://localhost:3306/student_db");
	       dataSource.setUsername("root");
	       dataSource.setPassword("");
	       return dataSource;
	}
	
   @Bean
    public SqlSessionFactoryBean sqlSessionFactory() {
	   SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
       sessionFactory.setDataSource(dataSource());
	   sessionFactory.setTypeAliasesPackage("com.java.spring.student.model");
	    //sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:com/java/spring/student/mapper/*.xml"));
	    //sessionFactory.setMapperLocations(new Resource[] { new ClassPathResource("com/java/spring/student/mapper/*.xml") });       
        return sessionFactory;
    }
   
}
