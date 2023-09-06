package com.woori.service.user;

import com.woori.domain.entity.User;
import com.woori.dto.user.AlluserInfoResponseDto;
import com.woori.dto.user.LoginRequestDto;

public interface UserService {
	
	public User login(LoginRequestDto user);
	
	public AlluserInfoResponseDto getAlluserInfo(int index);
}
