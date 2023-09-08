package com.woori.dto.account;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

	private String accountName;
	private String name;
	private Date birth;
	private String accountPw;
	private BigDecimal balance;
	//더 추가하기
	
}
