package com.mathan.is.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class DbConfiguration {

	@Bean
	public DataSource getDataSource() {
		return DataSourceBuilder.create().driverClassName("com.mysql.cj.jdbc.Driver").url(
				"jdbc:mysql://localhost:3306/services_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false")
				.username("root").password("root").build();
	}

	@Bean
	public Properties hiberProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		return properties;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		localContainerEntityManagerFactoryBean.setPackagesToScan("com.mathan.is");
		localContainerEntityManagerFactoryBean.setDataSource(getDataSource());
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
		localContainerEntityManagerFactoryBean.setJpaProperties(hiberProperties());
		return localContainerEntityManagerFactoryBean;
	}

}
