package com.woori.dto.order;

import java.math.BigDecimal;

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
//계좌 , 비밀번호 입력시 response 데이터
public class OrderAccountVerifyDto {
    private Long ipoId;
	private BigDecimal balance;
	private int orderableAmount;
    private BigDecimal slprc;	//공모가(확정발행가)
	
}
