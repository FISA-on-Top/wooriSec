package com.woori.domain.account;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.woori.domain.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

	Optional<Account> findByAccountNumAndAccountPw(String accountNum, String accountPw);

	public Account findByAccountNumAndAccountPwAndBirth(String accountNum, String accountPw, LocalDate birth);

	Optional<Account> findByAccountNum(String accountNum);
	
}
