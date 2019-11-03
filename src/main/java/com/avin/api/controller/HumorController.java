package com.avin.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getHumor(
			@PathVariable(name = "id", required = true) Long id,
			HttpServletRequest request, HttpServletResponse response){
		return ResponseEntity.ok(boardService.getDto(id));
	}
	
	@GetMapping(value = "")
	public ResponseEntity<?> getHumorCnt(
			Pageable pageable,
			HttpServletRequest request, HttpServletResponse response){
		return ResponseEntity.ok(boardService.getBoards(pageable));
	}
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity<?> patchHumor(
			@PathVariable(name = "id", required = true) Long id,
			@RequestBody BoardHumorDto dto,
			HttpServletRequest request, HttpServletResponse response){
		boardService.updateBoard(id, dto);
		return ResponseEntity.ok()
				.build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteHumor(
			@PathVariable(name = "id", required = true) Long id,
			HttpServletRequest request, HttpServletResponse response){
		boardService.deleteBoard(id);
		return ResponseEntity.ok()
				.build();
	}
}
