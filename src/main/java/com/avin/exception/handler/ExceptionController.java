package com.avin.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.avin.exception.SecurityUserNotFoundException;

@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

	@ExceptionHandler({
		SecurityUserNotFoundException.class,
		UsernameNotFoundException.class
	})
	@Nullable
	public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {
		HttpStatus status = null;
		if(ex instanceof SecurityUserNotFoundException) {
			status = HttpStatus.METHOD_NOT_ALLOWED;
		}else if(ex instanceof UsernameNotFoundException){
			status = HttpStatus.NOT_FOUND;
		}
		
		return ResponseEntity
				.status(status)
				.body(ex.getMessage());
	}
}
