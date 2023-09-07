package com.woori.service.account;

import com.woori.domain.entity.Account;
import com.woori.dto.user.SignupAccountRequestDto;

public interface AccountService {
	
	public Account verifyAccount(SignupAccountRequestDto requestDto);

}
