package com.woori.dto.ipo;

import java.math.BigDecimal;
import java.time.LocalDate;
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
public class IpoDetailResponseDto {

	// 종목 조회
	private Long ipoId;
	private int corpCode;
	private String corpName;	// 기업명
	private LocalDate sbd;
	private LocalDate pymd;
	private LocalDate refund;
	private Date ipoDate;
	private BigDecimal slprc;
	private String corpCls;	// 분류 (컬럼명을 자바의 네이밍 규칙에 맞게 수정)
	private Long stkcnt;
	private String capitalIncrease;	// 증자방법. //피그마에 대표주관회사로 되어있는데 우리는 컬럼에 없기때문에 어떻게 처리할지 논의 필요. 우선 증자방법으로 설정
	
}