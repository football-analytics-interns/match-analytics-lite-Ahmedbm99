package com.matchAnalytics.demo;

import org.springframework.boot.SpringApplication;

public class TestMatchAnalyticsApplication {

	public static void main(String[] args) {
		SpringApplication.from(MatchAnalyticsApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
