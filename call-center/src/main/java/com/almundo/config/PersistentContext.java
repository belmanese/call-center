package com.almundo.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.almundo.enums.ContextProperty;

@Configuration
@EnableJpaRepositories("com.almundo")
@ComponentScan("com.almundo")
@EnableTransactionManagement
@PropertySource("classpath:hibernate.properties")
public class PersistentContext {

	@Autowired
	private Environment environment;
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(
			environment.getRequiredProperty(ContextProperty.DB_DRIVER_CLASS_NAME.getPropertie()));
		dataSource.setUrl(
			environment.getRequiredProperty(ContextProperty.DB_URL.getPropertie()));
		dataSource.setUsername(
			environment.getRequiredProperty(ContextProperty.DB_USERNAME.getPropertie()));
		dataSource.setPassword(
			environment.getRequiredProperty(ContextProperty.DB_PASSWORD.getPropertie()));
		return dataSource;
	}

	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan(ContextProperty.PATH_ENTITY_SCAN.getPropertie());

		Properties jpaProperties = new Properties();
		jpaProperties.put(
			ContextProperty.HIBERNATE_DIALECT.getPropertie(),
			environment.getRequiredProperty(ContextProperty.HIBERNATE_DIALECT.getPropertie()));
		jpaProperties.put(
			ContextProperty.HIBERNATE_SHOW_SQL.getPropertie(),
			environment.getRequiredProperty(ContextProperty.HIBERNATE_SHOW_SQL.getPropertie()));
		jpaProperties.put(
			ContextProperty.HIBERNATE_FORMAT_SQL.getPropertie(),
			environment.getRequiredProperty(ContextProperty.HIBERNATE_FORMAT_SQL.getPropertie()));

		entityManagerFactoryBean.setJpaProperties(jpaProperties);

		return entityManagerFactoryBean;
	}

	@Bean public PlatformTransactionManager transactionManager() {
		JpaTransactionManager txManager= new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory(dataSource()).getObject());
		return txManager;
	}
	
}
