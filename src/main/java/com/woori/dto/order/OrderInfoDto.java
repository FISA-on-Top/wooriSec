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
    private String name;
    private String corpName;
    private int orderAmount;
    private BigDecimal slprc;
    private BigDecimal deposit;
    private String phoneNum;
    private Date refund;
    private Date pymd;
    

}
