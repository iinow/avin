package com.avin.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avin.entity.Memo;

public interface MemoRepository extends JpaRepository<Memo, Long>{

}
