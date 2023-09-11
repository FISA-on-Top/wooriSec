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
public class OrderDto {
	
	//유저아이디
	private String userId;
	private Long ipoId;
	//청약계좌 선택에서 필요한 정보
	private Long accountId;	//계좌번호
	private String accountPw;	//계좌 비밀번호
	private BigDecimal balance;	//잔액
	private int commission = 2000;	//청약수수료 : 2000원으로 통일, BigDecimal?
	private int orderableAmount;	//청약가능수량 : {(balance-2000)/(slprc/2)}
	
	//청약정보 입력에서 필요한 정보
    private BigDecimal slprc;	//확정발행가
    private int orderAmount;	//주문수량
    private BigDecimal deposit;	// = (orderAmount*slprc)/2;	//청약증거금
    private String phoneNum;	//휴대폰번호
    
}