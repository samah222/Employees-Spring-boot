package com.learningspringboot.samah.employees;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@EnableSwagger2
@SpringBootApplication

public class EmployeesApplication {
	private static final Logger logger = LoggerFactory.getLogger(EmployeesApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(EmployeesApplication.class, args);
	}

}
