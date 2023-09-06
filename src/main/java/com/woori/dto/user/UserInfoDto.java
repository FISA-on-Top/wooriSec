package com.woori.dto.user;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDto {
	private String userName;
	private LocalDate birth;
	private String userId;
	private String accountNum;
	private LocalDateTime createdAt;
	
}
