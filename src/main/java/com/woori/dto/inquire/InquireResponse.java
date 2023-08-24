package com.woori.dto.inquire;

import java.math.BigDecimal;
import java.util.Date;

import lombok.ToString;

@ToString
public class InquireResponse {

	// 우선 request, response 나누지 않고 InquireDto에 다 작업하겠음 ㅠ어려움
	//캘린더
	
	
	//종목 조회
	private String corp_cls;	//분류
	private String corp_name;	//기업명
	private String capital_increase;	//피그마에 대표주관회사로 되어있는데 우리는 컬럼에 없기때문에 어떻게 처리할지 논의 필요. 우선 증자방법으로 설정
	private Date sdb;
	private Date refund;
	private BigDecimal slprc;
	
	public InquireResponse(String corp_cls, String corp_name, String capital_increase, Date sdb, Date refund,
			BigDecimal slprc) {
		this.corp_cls = corp_cls;
		this.corp_name = corp_name;
		this.capital_increase = capital_increase;
		this.sdb = sdb;
		this.refund = refund;
		this.slprc = slprc;
	}
}
