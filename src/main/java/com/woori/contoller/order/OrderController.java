package com.woori.contoller.order;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.woori.dto.APIResponse;
import com.woori.dto.account.VerifyRequestDto;
import com.woori.dto.order.OrderAccountDto;
import com.woori.dto.order.OrderAccountVerifyDto;
import com.woori.dto.order.OrderApprovalRequestDto;
import com.woori.dto.order.OrderCancelDto;
import com.woori.dto.order.OrderInfoDto;
import com.woori.dto.order.OrderListDto;
import com.woori.dto.order.OrdersResponseDto;
import com.woori.service.order.OrderService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@ApiOperation(value = "종목 조회", notes = "API 설명 부분 : ipo 종목 조회")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), 
	@ApiResponse(code = 404, message = "404 에러 발생"),
	@ApiResponse(code = 500, message = "500 에러 발생") })

	// 해당 일자 클릭 시 해당 일자 신청 가능한 공모주 조회
	@GetMapping
	public ResponseEntity<APIResponse<OrdersResponseDto>> getAllIpoDetails(
			@RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate sbd, 
			@RequestParam(value = "index", required = false) int index) {
		
		return ResponseEntity.ok(APIResponse.success(orderService.getIposInfo(sbd, index)));
	}

	// 사용자 아이디 request header로 받아 해당 아이디에 대한 계좌번호 리턴(청약하기 버튼 클릭)
	@GetMapping("/account")
	public ResponseEntity<APIResponse<OrderAccountDto>> getAccountInfo(@RequestHeader String userId) throws Exception {
		OrderAccountDto accountDto = orderService.getAccountByUserId(userId);
		
		return ResponseEntity.ok(APIResponse.success(accountDto));
	}

	//청약 정보 입력 > 청약계좌 선택 > 계좌 비밀번호 확인버튼
	@PostMapping("/account/verify")
	public ResponseEntity<APIResponse<?>> verifyAccount(@RequestBody VerifyRequestDto dto) {

		OrderAccountVerifyDto verifyDto = orderService.getOrderableInfo(dto);
		
		if (dto != null) {
            return ResponseEntity.ok(APIResponse.success(verifyDto));
        } else {
        	return ResponseEntity.ok(APIResponse.failbyRequest("비밀번호가 일치하지 않습니다"));
        	//return ResponseEntity.badRequest().body("비밀번호가 일치하지 않습니다");
        }
	}

	// 청약 정보 입력 > ‘다음’ 버튼 클릭
	// 중간에 바뀔 수도??
	@PostMapping("/approval")
	public ResponseEntity<APIResponse<OrderInfoDto>> OrderInfo(@RequestBody OrderApprovalRequestDto RequestDto) throws Exception {
		OrderInfoDto orderInfoDto = orderService.setOrderInfo(RequestDto);
		return ResponseEntity.ok(APIResponse.success(orderInfoDto));
	}

	// 청약 결과 조회/취소 - 신청 결과 조회
    @GetMapping("/list")
    public ResponseEntity<APIResponse<List<OrderListDto>>> getOrderInfo(
    		@RequestHeader String userId, 
    		@RequestParam("date") 
    		@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) throws Exception {
        List<OrderListDto> orderList = orderService.getOrderList(userId, date);

        return ResponseEntity.ok(APIResponse.success(orderList));
    }

	// 청약 결과 조회/취소 - ‘실행’ 버튼 클릭
	@DeleteMapping("/cancel")
	public ResponseEntity<APIResponse<?>> cancelOrder(
			@RequestHeader String accountNum,
			@RequestHeader String accountPw,
			@RequestBody Map<String, Long> requestBody){
		
		try {
			Long orderId = requestBody.get("orderId");
			OrderCancelDto orderCancelDto = orderService.getCancelOrder(accountNum, accountPw, orderId);
			
//			return new ResponseEntity.ok(APIResponse.success(orderCancelDto));
			return new ResponseEntity<>((APIResponse.success(orderCancelDto)), HttpStatus.OK);
			
		} catch(IllegalArgumentException e) {
			
			return new ResponseEntity<>(APIResponse.failbyRequest(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	//예외처리
	@ExceptionHandler
	public String exceptMsg(Exception e) {
		e.printStackTrace();
		return "예외발생";
	}
		
}

