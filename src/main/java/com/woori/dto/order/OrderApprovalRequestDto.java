package com.woori.dto.order;

import java.math.BigDecimal;

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
public class OrderApprovalRequestDto {

    private Long ipoId;
    private int orderAmount;
    private String phoneNum;
    private BigDecimal deposit;
}
