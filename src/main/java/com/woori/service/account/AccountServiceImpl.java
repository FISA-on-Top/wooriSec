package com.woori.service.account;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woori.InvalidException;
import com.woori.domain.account.AccountRepository;
import com.woori.domain.entity.Account;
import com.woori.domain.entity.User;
import com.woori.domain.user.UserRepository;
import com.woori.dto.user.RegisterRequestDto;
import com.woori.dto.user.SignupAccountRequestDto;
import com.woori.dto.user.SignupAccountResponseDto;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	//회원가입 - 계좌 인증
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

	//회원가입 - ID 중복 확인
	public boolean userIdCheck(String userId) {
	    return !userRepository.existsById(userId);
	}
	
	@Override
    @Transactional
    public User SignUp(RegisterRequestDto requestDto) {
        if(!userIdCheck(requestDto.getUserId())) {
            throw new RuntimeException("UserID already exists!");
        }

        Account linkedAccount = accountRepository.findByAccountNum(requestDto.getAccountNum())            .orElseThrow(() -> new RuntimeException("Account not found!"));

        String status = "registered";
        
        User u = new User(
            requestDto.getUserId(),
            linkedAccount,
            linkedAccount.getName(),
            requestDto.getUserPw(),
            requestDto.getPhoneNum(),
            requestDto.getEmail(),
            linkedAccount.getBirth(),
            LocalDateTime.now(),
            status
        );

        userRepository.save(u);
        return u;
    }


}
	
