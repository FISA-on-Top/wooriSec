package com.woori.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private long orderId;

    @Column(name = "user_id", nullable = false, length = 20)
    private String userId;

    @Column(name = "ipo_id", nullable = false)
    private int ipoId;

    @Column(name = "order_amount")
    private int orderAmount;

    @Column(name = "status", length = 10)
    private String status;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "cancle_date")
    private Date cancleDate;

}
