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
public class OrderCancelDto {
	
	private BigDecimal balance;
	private int orderAmount;
    private String corpCls;
    //청약구분 = "상장"
    private BigDecimal deposit;
    private long orderId;
    private Date cancleDate;
	private int corpCode;
    private String corpName;
}
