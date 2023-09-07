package com.woori.service.account;

import com.woori.dto.user.SignupAccountRequestDto;
import com.woori.dto.user.SignupAccountResponseDto;

public interface AccountService {
	
	public SignupAccountResponseDto verifyAccount(SignupAccountRequestDto requestDto);

}
