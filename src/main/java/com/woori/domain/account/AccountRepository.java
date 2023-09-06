package com.woori.domain.account;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.woori.domain.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

//	@Query("SELECT a FROM Account a WHERE a.accountNum = ?1 AND a.accountPw = ?2")
//	Account findByAccountNumAndAccountPw(String accountNum, String accountPw);
	
//	Optional<Account> findAccountByNumAndPw(String accountNum, String accountPw);
	Optional<Account> findByAccountNumAndAccountPw(String accountNum, String accountPw);
	

}
