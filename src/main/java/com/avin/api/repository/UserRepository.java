package com.avin.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avin.common.util.Constants.PROVIDER;
import com.avin.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
	Optional<User> findByEmailAndProvider(String email, PROVIDER provider);
    Boolean existsByEmail(String email);
}
