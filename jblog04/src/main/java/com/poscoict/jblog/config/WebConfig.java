package com.poscoict.jblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.poscoict.config.web.FileUploadConfig;
import com.poscoict.config.web.MvcConfig;
import com.poscoict.config.web.SecurityConfig;
import com.poscoict.jblog.interceptor.BlogInterceptor;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.poscoict.jblog.controller", "com.poscoict.jblog.exception"})
@Import({MvcConfig.class, SecurityConfig.class, FileUploadConfig.class})

public class WebConfig extends WebMvcConfigurerAdapter{
	// blog interceptor
	@Bean
	public HandlerInterceptor blogInterceptor() {
		return new BlogInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(blogInterceptor()).addPathPatterns("/**")
										.excludePathPatterns("/")
										.excludePathPatterns("/assets/**")		
										.excludePathPatterns("/images/**")
										.excludePathPatterns("/user/**")
										.excludePathPatterns("/main/**");	
		}
	
}
