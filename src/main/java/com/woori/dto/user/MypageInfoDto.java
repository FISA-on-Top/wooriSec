package com.woori.dto.user;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MypageInfoDto {
	private String userName;
	private String userId;
	private LocalDate birth;
	private String phoneNum;
	private String email;
	private String accountNum;
}
