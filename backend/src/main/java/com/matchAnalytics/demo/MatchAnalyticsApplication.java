package com.matchAnalytics.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.matchAnalytics.model")
@EnableJpaRepositories(basePackages = "com.matchAnalytics.repository")

@ComponentScan(basePackages = {
        "com.matchAnalytics",      
        "com.matchAnalytics.config",    
        "com.matchAnalytics.service",   
        "com.matchAnalytics.controller" 
})

public class MatchAnalyticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatchAnalyticsApplication.class, args);
	}

}
