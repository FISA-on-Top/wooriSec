package com.woori.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.woori.dto.order.OrderApprovalRequestDto;

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
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ipo_id")
    private Ipo ipo;

    @Column(name = "order_amount")
    private int orderAmount;
    
    @Column(name = "orderable_amount")
    private int orderableAmount;

    @Column(name = "status", length = 10)
    private String status;

    @Column(name = "order_date", columnDefinition = "DATETIME")
    private LocalDateTime orderDate;

    @Column(name = "cancle_date", columnDefinition = "DATETIME")
    private LocalDateTime cancleDate;
    
    @Column(name = "phone_num")
    private String phoneNum;
    
    @Column(name = "deposit", precision = 18, scale = 2)
	private BigDecimal deposit;	//청약증거금
    
    //서비스로 메서드 하나 옮긴 후 지우면 good
    public Orders(OrderApprovalRequestDto orderApprovalRequestDto, String userId) {
    	this.orderAmount = orderApprovalRequestDto.getOrderAmount();//orderAmount
    	this.status = "";//status
    	this.orderDate = orderApprovalRequestDto.getOrderDate();//orderDate
    	this.cancleDate = null;//cancelDate
    	this.phoneNum = orderApprovalRequestDto.getPhoneNum();//phoneNum
    	this.deposit = orderApprovalRequestDto.getDeposit();//deposit
    }

}
