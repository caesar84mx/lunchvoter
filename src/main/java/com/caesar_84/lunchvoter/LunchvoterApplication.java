package com.caesar_84.lunchvoter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class LunchvoterApplication {

	public static void main(String[] args) {
		SpringApplication.run(LunchvoterApplication.class, args);
	}
}
