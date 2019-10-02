package com.avin.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.avin.api.service.MemoService;
import com.avin.dto.MemoDTO;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/memos")
public class MemoController {

	@Autowired
	private MemoService memoService;
	
	@PostMapping("")
	public ResponseEntity<?> postMemo(
			@RequestBody(required = true) MemoDTO memo){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		System.out.println(auth);
		return ResponseEntity.ok(auth);
	}
}
