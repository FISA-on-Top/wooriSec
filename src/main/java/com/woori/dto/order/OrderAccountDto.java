package com.woori.dto.order;

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
//사용자 아이디 입력시 사용자 계좌번호 response
public class OrderAccountDto {
	private String accountNum;
}
