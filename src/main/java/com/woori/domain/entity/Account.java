package com.woori.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="account")
@ToString
@Entity	//JPA를 사용할 클래스 명시, 테이블과 매핑 역할
public class Account {
	
	@Id	//기본키(PK) 명시
	@GeneratedValue(strategy = GenerationType.IDENTITY) //1,2,3,... 자동 생성 어노테이션
    
	@Column(name = "account_id")
	private Long accountId;
    
	@Column(name = "account_num", unique = true, nullable = false, length = 15)
	private String accountNum;
    
	@Column(name = "name", nullable = false, length = 20)
	private String name;
    
	@Column(name = "birth", columnDefinition = "DATE", nullable = false)
	private LocalDate birth;
    
	@Column(name = "account_pw", nullable = false, length = 50)
	private String accountPw;
    
	@Column(name = "balance", nullable = false, precision = 18, scale = 2)
	private BigDecimal balance;
	

}
