package com.social.kata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.metrics.ApplicationStartup;

@SpringBootApplication
@ComponentScan(excludeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        value = { ApplicationStartup.class, 
                 KataApplication.class }))
public class TestKataApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(TestKataApplication.class, args);
	}
}
