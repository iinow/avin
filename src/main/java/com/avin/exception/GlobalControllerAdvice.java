package com.avin.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.log4j.Log4j2;

@Log4j2(topic = "GlobalControllerAdvice")
@ControllerAdvice
public class GlobalControllerAdvice {

	@ExceptionHandler(NullPointerException.class)
	public void nullPointerException(NullPointerException e) {
		log.info("null pointer: "+e.getMessage());
	}
	
	@ExceptionHandler(SignatureException.class)
	public void signatureException(SignatureException e) {
		log.info("signatureException: "+e.getMessage());
	}
	
	@ExceptionHandler(MalformedJwtException.class)
	public void malformedJwtException(MalformedJwtException e) {
		log.info("malformedJwtException: "+e.getMessage());
	}
	
	@ExceptionHandler(ExpiredJwtException.class)
	public void expiredJwtException(ExpiredJwtException e) {
		log.info("expiredJwtException: "+e.getMessage());
	}
	
	@ExceptionHandler(UnsupportedJwtException.class)
	public void unsupportedJwtException(UnsupportedJwtException e) {
		log.info("unsupportedJwtException: "+e.getMessage());
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public void illegalArgumentException(IllegalArgumentException e) {
		log.info("illegalArgumentException: "+e.getMessage());
	}
}
