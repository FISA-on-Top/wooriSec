package com.woori.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "ipo")	//테이블 명 작성
public class Ipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ipo_id")
    private Long ipoId;

    @Column(name = "corp_code", nullable = false)
    private int corpCode;

    @Column(name = "corp_name", nullable = false, length = 100)
    private String corpName;

    @Column(name = "sbd", columnDefinition = "DATE")
    private LocalDate sbd;
    
    @Column(name = "pymd", columnDefinition = "DATE")
    private LocalDate pymd;

    @Column(name = "refund", columnDefinition = "DATE")
    private LocalDate refund;

    @Column(name = "ipo_date")
    private Date ipoDate;	//date, datetime 고려해보기

    @Column(name = "slprc", precision = 8, scale = 2)
    private BigDecimal slprc;

    @Column(name = "corp_cls", length = 10)
    private String corpCls;

    @Column(name = "stkcnt")
    private Long stkcnt;

    @Column(name = "capital_increase", length = 10)
    private String capitalIncrease;

    
}
	
