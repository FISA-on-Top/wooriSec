//package com.woori.dto.inquire;
//
//import java.math.BigDecimal;
//import java.util.Date;
//
//import com.woori.domain.entity.Ipo;
//
//public class InquireDto2 {
//
//	//캘린더
//	
//	
//	//종목 조회
//	private String corp_cls;	//분류
//	private String corp_name;	//기업명
//	private String capital_increase;	//피그마에 대표주관회사로 되어있는데 우리는 컬럼에 없기때문에 어떻게 처리할지 논의 필요. 우선 증자방법으로 설정
//	private Date sdb;
//	private Date refund;
//	private BigDecimal slprc;
//	
//	public InquireDto2(String corp_cls, String corp_name, String capital_increase, Date sdb, Date refund,
//			BigDecimal slprc) {
//		this.corp_cls = corp_cls;
//		this.corp_name = corp_name;
//		this.capital_increase = capital_increase;
//		this.sdb = sdb;
//		this.refund = refund;
//		this.slprc = slprc;
//	}
//	
//	public Ipo toEntity() {
//		return new Ipo(ipoId: null, corpCode, corpName, sbd, pymd, refund, ipoDate, slprc, corpCls, stkcnt, capitalIncrease)
//	}
//}
