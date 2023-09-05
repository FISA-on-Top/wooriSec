package com.woori.domain.user;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.woori.domain.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
	//로그인 --- ?
	Optional<User> findByuserId(String userId);
	
	//계좌조회
	Optional<User> findByUserId(String userId);
	
	User findByBalance(BigDecimal balance);
	
	//select * from user where id=? and pw=?
	Optional<User> findByUserIdAndUserPw(String userId, String userPw);
	
}
