package com.woori.dto.order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

public class OrderListDto {
    
	private String status;
    private int orderAmount;
    private String corpCls;
//    private String subsClassification; 노션, 협의필요
    private BigDecimal deposit;
	private int commission = 2000;	//청약수수료 : 2000원으로 통일
    private long orderId;
    private LocalDateTime orderDate; //Date gpt의 추천
	private int corpCode;
    private String corpName;

}
