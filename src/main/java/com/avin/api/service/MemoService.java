package com.avin.api.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avin.api.repository.MemoRepository;
import com.avin.common.modelmapper.DomainMapper;
import com.avin.dto.MemoDto;
import com.avin.entity.Memo;
import com.avin.entity.User;
import com.avin.security.SpringSecurityUserContext;

@Service
public class MemoService {

	@Autowired
	private MemoRepository memoRepository;
	
	@Autowired
	private DomainMapper domainMapper;
	
	@Autowired
	private SpringSecurityUserContext userContext;
	
	@Transactional
	public Memo addMemo(Memo memo) throws Exception {
		return memoRepository.save(memo);
	}
	
	@Transactional
	public Memo addMemo(MemoDto memoDto) throws Exception {
		User user = userContext.getCurrentUser();
		Memo m = domainMapper.convertToDomain(memoDto, Memo.class);
		m.setUser(user);
		m = addMemo(m);
		user.getMemos().add(m);
		return m;
	}
	
	public Optional<Memo> getMemo(Long id) {
		return memoRepository.findById(id);
	}
	
	@Transactional
	public void setMemo(MemoDto dto) {
		Memo memo = domainMapper.convertToDomain(dto, Memo.class);
		getMemo(dto.getId()).ifPresent(m -> {
			m.setContent(memo.getContent());
		});
	}
}
