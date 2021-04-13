package com.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.employee.service.util.Password;

@SpringBootApplication
public class EmployeeManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementServiceApplication.class, args);
	}
	
	@Bean
	public Password getPasswordBean()
	{
		return new Password();
	}

}
