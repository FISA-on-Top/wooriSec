package com.woori.domain.entity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name = "user")
@ToString
@Entity
public class User {

    @Id
    @Column(name = "user_id", nullable = false, length = 20)
    private String userId;
    
    @OneToOne
    @JoinColumn(name="account_id")
    private Account account;
    
    @Column(name = "user_name", length = 20)
    private String userName;
    
    @Column(name = "user_pw", nullable = false, length = 50)
    private String userPw;
    
    @Column(name = "phone_num", nullable = false, length = 20)
    private String phoneNum;
    
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    
    @Column(name = "birth", columnDefinition = "DATE")
    private LocalDate birth;
    
    @Column(name = "created_at", columnDefinition = "DATETIME", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(name = "status", nullable = false, length = 10)
    private String status;
}