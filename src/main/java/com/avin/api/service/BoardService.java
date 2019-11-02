package com.avin.api.service;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.avin.api.repository.BoardHumorRepository;
import com.avin.common.modelmapper.DomainMapper;
import com.avin.dto.BoardHumorDto;
import com.avin.entity.BoardHumor;
import com.avin.entity.User;
import com.avin.exception.ResourceNotFoundException;
import com.avin.security.SpringSecurityUserContext;

/**
 * 추후에는 아래 어노테이션으로 검사를 하도록 하자
 * @Secured({"ROLE_USER"})
 * */
@Service
public class BoardService {

	private SpringSecurityUserContext userContext;
	
	private BoardHumorRepository boardHumorRepository;
	
	private DomainMapper domainMapper;
	
	public BoardService(
			final BoardHumorRepository boardHumorRepository, 
			final SpringSecurityUserContext userContext,
			final DomainMapper domainMapper) {
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
		return add(board);
	}
	
	public BoardHumor get(Long id) {
		return boardHumorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("board humor", "id", id));
	}
	
	public BoardHumorDto getDto(Long id) {
		BoardHumor board = get(id);
		return domainMapper.convertToDomain(board, BoardHumorDto.class);
	}
	
	public long getBoardCnt() {
		return boardHumorRepository.count();
	}
	
	public Page<BoardHumorDto> getBoards(Pageable page){
		return boardHumorRepository.findAll(page)
				.map(board -> domainMapper.convertToDomain(board, BoardHumorDto.class));
	}
}
