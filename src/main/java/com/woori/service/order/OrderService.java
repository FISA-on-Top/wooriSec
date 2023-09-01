package com.woori.service.order;

import java.util.Date;
import java.util.List;

import com.woori.dto.order.OrderAccountDto;
import com.woori.dto.order.OrderListDto;
import com.woori.dto.order.OrderableDto;

public interface OrderService {

//	//공모주 신청 - 청약계좌, 청약정보 입력 페이지
//	OrderDto createOrder(OrderDto orderDto);
    
    //신청 가능 공모주 모두 조회
    public List<OrderableDto> getIposInfo(Date date);
	
    //유저아이디 입력시 해당 계좌 정보 출력
    public OrderAccountDto getAccountByUserId(String accountNum);
    
    public OrderListDto getOrderList(String userId);
}