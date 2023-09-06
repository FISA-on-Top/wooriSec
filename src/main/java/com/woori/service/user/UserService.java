package com.woori.service.user;

import com.woori.domain.entity.User;
import com.woori.dto.user.AlluserInfoResponseDto;
import com.woori.dto.user.LoginRequestDto;
import com.woori.dto.user.MypageInfoDto;
import com.woori.dto.user.MypageUpdateRequestDto;

public interface UserService {
	
	public User login(LoginRequestDto user);
	
	public AlluserInfoResponseDto getAlluserInfo(int index);
	
	public MypageInfoDto getUserInfoById(String id);
	
	public MypageInfoDto updateUserInfoById(String id, MypageUpdateRequestDto requestBody);
}
