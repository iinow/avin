package com.avin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * JDK Auto proxy 사용 오버라이딩한 메서드 aop 적용 필요
 * */
@EnableWebSecurity(debug = false)
@SpringBootApplication
@Order(Ordered.LOWEST_PRECEDENCE)
public class AvinApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvinApplication.class, args);
	}
}
