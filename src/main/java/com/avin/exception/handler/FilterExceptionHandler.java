package com.avin.exception.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.avin.dto.ErrorDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class FilterExceptionHandler extends ResponseEntityExceptionHandler {

//	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
//	@ExceptionHandler(ExpiredJwtException.class)
	public @ResponseBody ResponseEntity<?> handleJwtException(HttpServletRequest request, Exception e) {
		ErrorDto er = new ErrorDto();
		
		if(e instanceof SignatureException) {
			er.setError("Invalid JWT signature");
		}else if(e instanceof MalformedJwtException) {
			er.setError("Invalid JWT token");
		}else if(e instanceof ExpiredJwtException) {
			er.setError("Expired JWT token");
		}else if(e instanceof UnsupportedJwtException) {
			er.setError("Unsupported JWT token");
		}else if(e instanceof IllegalArgumentException) {
			er.setError("JWT claims string is empty");
		}
		
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(er);
	}
}
