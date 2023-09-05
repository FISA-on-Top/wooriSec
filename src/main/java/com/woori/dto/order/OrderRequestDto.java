package com.woori.dto.order;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class OrderRequestDto {

    private Long ipoId;
    private int orderAmount;
    private String phoneNum;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderDate;
    private BigDecimal deposit;  // 이 부분은 문자열로 받아와서 처리하는 것이 나을 수 있습니다. - chatGPT

}
