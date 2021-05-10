package com.hotel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableHystrix

public class CoreHotelManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreHotelManagementServiceApplication.class, args);
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate getTemplate()
	{
		return new RestTemplate();
	}
	
	@Bean
	public Logger getLogger()
	{
		return LoggerFactory.getLogger(CoreHotelManagementServiceApplication.class);
	}
}
