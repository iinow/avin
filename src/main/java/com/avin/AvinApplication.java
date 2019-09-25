package com.avin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity(debug = false)
@SpringBootApplication
public class AvinApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvinApplication.class, args);
	}
}
