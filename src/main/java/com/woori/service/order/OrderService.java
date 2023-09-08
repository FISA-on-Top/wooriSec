package com.woori.service.order;

import java.time.LocalDate;
import java.util.List;

import com.woori.dto.account.VerifyRequestDto;
import com.woori.dto.order.OrderAccountDto;
import com.woori.dto.order.OrderAccountVerifyDto;
import com.woori.dto.order.OrderApprovalRequestDto;
import com.woori.dto.order.OrderCancelDto;
import com.woori.dto.order.OrderInfoDto;
import com.woori.dto.order.OrderListDto;
import com.woori.dto.order.OrdersResponseDto;


public interface OrderService {

//	//공모주 신청 - 청약계좌, 청약정보 입력 페이지
//	OrderDto createOrder(OrderDto orderDto);
    
    //신청 가능 공모주 모두 조회
    public OrdersResponseDto getIposInfo(LocalDate date, int index);
	
    //유저아이디 입력시 해당 계좌 정보 출력
    public OrderAccountDto getAccountByUserId(String accountNum);

    //계좌와 비밀번호 검증
    public OrderAccountVerifyDto getOrderableInfo(VerifyRequestDto requestDto);

	//유저 잔액조회
    
    //청약정보 입력 후 다음버튼 클릭 -> 청약 정보
    public OrderInfoDto setOrderInfo(OrderApprovalRequestDto orderApprovalRequestDto);
    
    //userId입력 시 신청결과 조회 
    public List<OrderListDto> getOrderList(String userId, LocalDate date);

    
    //청약결과 조회/취소 - '실행'버튼 클릭
    public OrderCancelDto getCancelOrder(String accountNum, String accountPw, Long orderId);

}