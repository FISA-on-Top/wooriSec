package com.woori.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MypageUpdateRequestDto {
	
    private String email;
    private String phoneNum;
    private String userPw;

}
