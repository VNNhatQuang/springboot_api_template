package com.example.springboot_api_template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringbootApiTemplateApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringbootApiTemplateApplication.class, args);
		String port = context.getEnvironment().getProperty("server.port");
		System.out.printf("APIs is running:  http://localhost:%s/swagger-ui", port);
	}

}
