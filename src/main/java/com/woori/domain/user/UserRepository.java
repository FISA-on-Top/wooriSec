package com.woori.domain.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.woori.domain.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findAll();
	
	//계좌조회
	Optional<User> findByUserId(String userId);
}
