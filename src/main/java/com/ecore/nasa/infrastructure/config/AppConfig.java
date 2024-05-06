package com.ecore.nasa.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class AppConfig implements WebMvcConfigurer {

	public void addCorsMappings(CorsRegistry registry){
		registry.addMapping("/**").allowedMethods("GET");
	}

}
