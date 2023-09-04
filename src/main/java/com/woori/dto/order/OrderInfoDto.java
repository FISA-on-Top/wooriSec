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

public class OrderInfoDto {
	
    private Long ipoId;
    private String corpName;
    private int orderAmount;
    private BigDecimal deposit;
    private Date refund;
	private String name;
    private BigDecimal slprc;
    private Long accountId;
    private Date pymd;
    

	

}
