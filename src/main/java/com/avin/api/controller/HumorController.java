package com.avin.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avin.api.service.BoardService;
import com.avin.dto.BoardHumorDto;

/**
 * @author BISHOP
 * @since 2019.11.01
 * */
@RestController
@RequestMapping(value = "/boards/humors")
public class HumorController {
	
	private BoardService boardService;
	
	public HumorController(BoardService boardService) {
		this.boardService = boardService;
	}

	@PostMapping(value = "")
	public ResponseEntity<?> postHumor(
			@RequestBody BoardHumorDto dto,
			HttpServletRequest request, HttpServletResponse response){
		return ResponseEntity.ok(boardService.add(dto));
	}
}
