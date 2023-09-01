package com.woori.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.woori.domain.entity.Order;

//Order 엔터티와 관련된 CRUD 연산을 위한 JPA 레포지토리
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
