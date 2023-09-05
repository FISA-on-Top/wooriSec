package com.woori.contoller.order;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.woori.dto.account.VerifyRequestDto;
import com.woori.dto.order.OrderAccountDto;
import com.woori.dto.order.OrderAccountVerifyDto;
import com.woori.dto.order.OrderCancelDto;
import com.woori.dto.order.OrderInfoDto;
import com.woori.dto.order.OrderListDto;
import com.woori.dto.order.OrderRequestDto;
import com.woori.dto.order.OrderableDto;
import com.woori.service.order.OrderServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderServiceImpl orderService;

	@ApiOperation(value = "종목 조회", notes = "API 설명 부분 : ipo 종목 조회")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 404, message = "404 에러 발생"),
			@ApiResponse(code = 500, message = "500 에러 발생") })

	// 해당 일자 클릭 시 해당 일자 신청 가능한 공모주 조회
	@GetMapping
	public ResponseEntity<List<OrderableDto>> getAllIpoDetails(
			@RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate sbd) {
		List<OrderableDto> orderableDtos = orderService.getIposInfo(sbd);
		return ResponseEntity.ok(orderableDtos);
	}

	// 사용자 아이디 request header로 받아 해당 아이디에 대한 계좌번호 리턴(청약하기 버튼 클릭)
	@GetMapping("/account")
	public ResponseEntity<OrderAccountDto> getAccountInfo(@RequestHeader String userId) throws Exception {
		OrderAccountDto accountDto = orderService.getAccountByUserId(userId);
		return ResponseEntity.ok(accountDto);
	}

	// 청약 정보 입력 > 청약계좌 선택 > 계좌 비밀번호 [확인]버튼
//	@GetMapping("/account/verify")

	// 청약 정보 입력 > ‘다음’ 버튼 클릭
	// 중간에 바뀔 수도??
	@PostMapping("/approval")
	public ResponseEntity<OrderInfoDto> OrderInfo(@RequestBody OrderRequestDto orderRequestDto) throws Exception {
		OrderInfoDto orderInfoDto = orderService.setOrderInfo(orderRequestDto);
		return ResponseEntity.ok(orderInfoDto);

	}

	// 청약 결과 조회/취소 - 신청 결과 조회
	@GetMapping("/list")
	public ResponseEntity<OrderListDto> getOrderInfo(@RequestHeader Long userId) throws Exception {
		OrderListDto orderListDto = orderService.getOrderList(userId);
		//return ResponseEntity.ok(orderListDto);
		return null;
	}

	// 청약 결과 조회/취소 - ‘취소’ 버튼 클릭
//	@GETMapping("/{userId})

	// 청약 결과 조회/취소 - ‘실행’ 버튼 클릭
	@GetMapping("/cancel")
	public ResponseEntity<OrderCancelDto> cancelOrder(
			@RequestHeader(value = "AccountNum", required = false) String accountNum,
			@RequestHeader(value = "AccountPw", required = false) String accountPw){
		OrderCancelDto orderCancelDto = orderService.getcancelOrder(accountNum, accountPw);

		return ResponseEntity.ok(orderCancelDto);
	}
	
	//청약 정보 입력 > 청약계좌 선택 > 계좌 비밀번호 확인버튼
	@GetMapping("/account/verify")
	public ResponseEntity<?> verifyAccount(
			@RequestHeader VerifyRequestDto dto) {

		OrderAccountVerifyDto verifyDto = orderService.getOrderableInfo(dto);
		
		if (verifyDto != null) {
            return ResponseEntity.ok(verifyDto);
        } else {
        	//인증 실패
            return ResponseEntity.badRequest().body("비밀번호가 일치하지 않습니다");
        }
	}
	
	
	//예외처리
	@ExceptionHandler
	public String exceptMsg(Exception e) {
		e.printStackTrace();
		return "예외발생";
	}
		
}

