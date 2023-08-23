package com.woori.dto.inquire;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class InquireDto {

	// 종목 조회
//	private Long ipoId;
	private String corpCls;	// 분류 (컬럼명을 자바의 네이밍 규칙에 맞게 수정)
	private String corpName;	// 기업명
	private String capitalIncrease;	// 증자방법
	private Date sbd;
	private Date refund;
	private BigDecimal slprc;

	public InquireDto(String corpCls, String corpName, String capitalIncrease, Date sbd, Date refund, BigDecimal slprc) {
//		this.ipoId = ipoId;
		this.corpCls = corpCls;
		this.corpName = corpName;
		this.capitalIncrease = capitalIncrease;
		this.sbd = sbd;
		this.refund = refund;
		this.slprc = slprc;
	}
	
}