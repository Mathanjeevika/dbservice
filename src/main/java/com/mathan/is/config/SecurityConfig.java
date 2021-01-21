package com.mathan.is.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mathan.is.config.filter.AuthenticationFilter;

@Configuration
public class SecurityConfig {

	@Autowired
	private AuthenticationFilter authenticationFilter;
	
	@Bean
	public FilterRegistrationBean<AuthenticationFilter> authFilterRegistrationBean(){
		FilterRegistrationBean<AuthenticationFilter> auFilterRegistrationBean = new FilterRegistrationBean<AuthenticationFilter>();
		auFilterRegistrationBean.setFilter(authenticationFilter);
		auFilterRegistrationBean.addUrlPatterns("*");
		auFilterRegistrationBean.setOrder(1);
		return auFilterRegistrationBean;
	}
	
}
