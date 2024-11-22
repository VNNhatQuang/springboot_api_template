package com.example.springboot_api_template.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.springboot_api_template.middleware.JwtAuthMiddleware;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private JwtAuthMiddleware jwtAuthMiddleware;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// Middleware register
		registry.addInterceptor(jwtAuthMiddleware).addPathPatterns("/api/user/**");
	}
}
