package com.woori.dto.order;

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
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderableDto { // ipoDto와 동일

	// 종목 조회
	private Long ipoId;
	private String corpCls; // 분류 (컬럼명을 자바의 네이밍 규칙에 맞게 수정)
	private String corpName; // 기업명
	private Date sbd;
	private Date refund;
	private BigDecimal slprc;
}
