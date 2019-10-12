package com.avin.api.controller;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avin.api.service.MemoService;
import com.avin.dto.MemoDto;

@RestController
@RequestMapping("/memos")
public class MemoController {

	@Autowired
	private MemoService memoService;
	
	@PostMapping("")
	public ResponseEntity<?> postMemo(
			@RequestBody(required = true) MemoDto memo,
			ServletRequest request, ServletResponse response){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		try {
			return ResponseEntity.ok(memoService.addMemo(memo));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	
}
