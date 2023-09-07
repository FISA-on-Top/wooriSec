package com.woori.service.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woori.InvalidException;
import com.woori.domain.account.AccountRepository;
import com.woori.domain.entity.Account;
import com.woori.dto.user.SignupAccountRequestDto;
import com.woori.dto.user.SignupAccountResponseDto;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountRepository accountRepository;
	
	@Override
    public SignupAccountResponseDto verifyAccount(SignupAccountRequestDto requestDto) throws InvalidException {
        Account account = accountRepository.findByAccountNumAndAccountPwAndBirth(
            requestDto.getAccountNum(),
            requestDto.getAccountPw(),
            requestDto.getBirth()
        );

        if (account == null) {
            throw new InvalidException("계좌 정보가 일치하지 않습니다.", 1003);
        }

        return new SignupAccountResponseDto(account.getName());
    }
	}
	
