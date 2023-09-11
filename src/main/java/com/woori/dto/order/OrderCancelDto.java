package com.woori.dto.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
	
	private BigDecimal balance; //Account
	private int orderAmount; //orders
    private String corpCls; //IPO
    //청약구분 = "상장"
    private BigDecimal deposit; //orders
    private long orderId; //orders
    private LocalDateTime cancelDate; //시간까지 표시 update
	private int corpCode; //ipo
    private String corpName; // ipo
}
