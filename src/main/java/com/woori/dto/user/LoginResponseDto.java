package com.woori.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class LoginResponseDto {
	private String resultCode;
	private String resultMessage;
	private String userId;
	
}
