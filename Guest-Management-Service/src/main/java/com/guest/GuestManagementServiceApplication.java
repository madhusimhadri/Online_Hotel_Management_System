package com.guest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class GuestManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuestManagementServiceApplication.class, args);
	}

}
