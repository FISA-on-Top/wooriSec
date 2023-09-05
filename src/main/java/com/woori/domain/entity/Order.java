package com.woori.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.woori.dto.order.OrderRequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long orderId;

    @Column(name = "user_id", nullable = false, length = 20)
    private String userId;

    @Column(name = "ipo_id", nullable = false)
    private long ipoId;

    @Column(name = "order_amount")
    private int orderAmount;

    @Column(name = "status", length = 10)
    private String status;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "cancle_date")
    private Date cancleDate;
    
    @Column(name = "phone_num")
    private String phoneNum;
    
    @Column(name = "deposit")
    private BigDecimal deposit;
    
//    public Order(OrderInfoDto orderInfoDto) {
////    	this.orderId = orderInfoDto.get//orderId
//    	this.userId = //userId
//    	this.ipoId//ipoId
//    	this.orderAmount//orderAmount
//    	this.status = //status
//    	this.orderDate = //orderDate
//    	this.cancleDate//cancelDate
//    	this.phoneNum = orderInfoDto.get;//phoneNum
//    	this.deposit = orderInfoDto.getDeposit()//deposit
//    }
    public Order(OrderRequestDto orderRequestDto, String userId) {
    	this.orderId = orderId;//orderId
    	this.userId = userId;//userId
    	this.ipoId = orderRequestDto.getIpoId();//ipoId
    	this.orderAmount = orderRequestDto.getOrderAmount();//orderAmount
    	this.status = "";//status
    	this.orderDate = orderRequestDto.getOrderDate();//orderDate
    	Date date = new Date();
    	this.cancleDate = date;//cancelDate
    	this.phoneNum = orderRequestDto.getPhoneNum();//phoneNum
    	this.deposit = orderRequestDto.getDeposit();//deposit
    }

}
