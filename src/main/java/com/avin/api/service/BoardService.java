package com.avin.api.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.avin.api.repository.BoardHumorRepository;
import com.avin.common.modelmapper.DomainMapper;
import com.avin.dto.BoardHumorDto;
import com.avin.entity.Board;
import com.avin.entity.BoardHumor;
import com.avin.entity.User;
import com.avin.security.SpringSecurityUserContext;

@Service
public class BoardService {

	private SpringSecurityUserContext userContext;
	
	private BoardHumorRepository boardHumorRepository;
	
	private DomainMapper domainMapper;
	
	public BoardService(
			BoardHumorRepository boardHumorRepository, 
			SpringSecurityUserContext userContext,
			DomainMapper domainMapper) {
		this.boardHumorRepository = boardHumorRepository;
		this.userContext = userContext;
		this.domainMapper = domainMapper;
	}
	
	@Transactional
	public BoardHumor add(BoardHumor board) {
		return boardHumorRepository.save(board);
	}
	
	@Transactional
	public BoardHumor add(BoardHumorDto dto) {
		User user = userContext.getCurrentUser();
		BoardHumor board = domainMapper.convertToDomain(dto, BoardHumor.class);
		board.setRegisterUser(user);
		return boardHumorRepository.save(board);
	}
}
