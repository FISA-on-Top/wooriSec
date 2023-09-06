package com.woori.service.user;

import com.woori.domain.entity.User;
import com.woori.dto.user.AlluserInfoResponseDto;
import com.woori.dto.user.LoginRequestDto;
import com.woori.dto.user.MypageInfoDto;

public interface UserService {
	
	public User login(LoginRequestDto user);
	
	public AlluserInfoResponseDto getAlluserInfo(int index);
	
	public MypageInfoDto getUserInfoById(String id);
}
