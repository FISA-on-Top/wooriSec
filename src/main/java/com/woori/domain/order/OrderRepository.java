package com.woori.domain.order;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.woori.domain.entity.Orders;
import com.woori.dto.order.OrderApprovalRequestDto;

//Order 엔터티와 관련된 CRUD 연산을 위한 JPA 레포지토리
@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

	void save(OrderApprovalRequestDto orderApprovalRequestDto);

	@Query(value = "SELECT * FROM orders WHERE user_id=:userId AND DATE_FORMAT(order_date, '%Y-%m-%d')=:date", nativeQuery = true)
	List<Orders> findByUserIdAndOrderDate(@Param("userId") String userId, @Param("date") LocalDate date);
	
}
