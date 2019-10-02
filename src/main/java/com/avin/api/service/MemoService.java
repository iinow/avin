package com.avin.api.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avin.api.repository.MemoRepository;
import com.avin.entity.Memo;

@Service
public class MemoService {

	@Autowired
	private MemoRepository memoRepository;
	
	@Transactional
	public Memo addMemo(Memo memo) {
		return memoRepository.save(memo);
	}
	
	public Optional<Memo> getMemo(Long id) {
		return memoRepository.findById(id);
	}
}
