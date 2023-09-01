package com.woori.contoller.order;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.woori.dto.order.OrderAccountDto;
import com.woori.dto.order.OrderableDto;
import com.woori.service.order.OrderService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@ApiOperation(value = "신청 가능한 종목 조회", notes = "API 설명 부분 : 신청 가능한 ipo 종목 조회")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "OK !!"),
		@ApiResponse(code = 404, message = "404 에러 발생시 출력 메세지, Not Found !"),
		@ApiResponse(code = 500, message = "500 에러 발생시 출력 메세지, 가령 Internal Server Error !")
	})
	
	// 해당 일자 클릭 시 해당 일자 신청 가능한 공모주 조회
	@GetMapping("/orders")
	public ResponseEntity<List<OrderableDto>> getAllIpoDetails(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date ipoDate) {
        List<OrderableDto> orderableDtos = orderService.getIposInfo(ipoDate);
        return ResponseEntity.ok(orderableDtos);
    }


	@GetMapping("/orders/account")
	public ResponseEntity<OrderAccountDto> getAccountInfo(@RequestHeader String userId) {
        OrderAccountDto accountDto = orderService.getAccountByUserId(userId);
        return ResponseEntity.ok(accountDto);
    }
}