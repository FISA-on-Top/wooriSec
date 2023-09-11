package com.woori.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VerifyRequestDto {
	private String accountNum;
	private String accountPw;
    private Long ipoId;


}
