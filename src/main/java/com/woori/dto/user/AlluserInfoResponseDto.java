package com.woori.dto.user;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AlluserInfoResponseDto {
	private final int totalPage;
	private final int currentPage;
	private final List<UserInfoDto> userInfo;
	
	public static AlluserInfoResponseDto responseData(int totalPage, int currentPage, List<UserInfoDto> userInfo) {
		return new AlluserInfoResponseDto(totalPage, currentPage, userInfo);
	}

}