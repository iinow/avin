package com.avin.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class AuthAspect {
//	@Pointcut("execution(* com.avin.security.filter.TokenAuthenticationFilter.*(..))")
//	@Pointcut("execution(* com.avin.api.controller.*.*(..))")
//    public void filterPointcut() {}
	
//	@Pointcut("within(* com.avin.security.filter.TokenAuthenticationFilter.*(..))")
//	public void filterPointcut() {
//		
//	}

//	@AfterThrowing(value = "within(com.avin.security.filter.TokenAuthenticationFilter.*)", throwing = "e")
//	@AfterThrowing(pointcut = "execution(* com.avin.security.filter.TokenAuthenticationFilter.*(..))", throwing = "e")
	public void exception(Exception e) {
        log.info("() 메소드 수행 중 예외 발생!");

        if (e instanceof Exception) {
            System.out.println("SQL 수행 중 예외 발생");
        } else {
            System.out.println("기타 예외 발생");
        }
	}
}
