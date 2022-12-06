package com.dh.gatewayapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(exclude = ReactiveUserDetailsServiceAutoConfiguration.class)
@EnableEurekaClient
public class GatewayApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApiApplication.class, args);
	}



}
