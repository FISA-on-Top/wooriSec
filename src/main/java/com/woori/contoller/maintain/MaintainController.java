package com.woori.contoller.maintain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.woori.dto.APIResponse;
import com.woori.dto.user.AlluserInfoResponseDto;
import com.woori.service.user.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/alluserinfo")
public class MaintainController {
	
    @Autowired
	private UserService userService;	//@Service로 선언된 클래스의 인스턴스(ServiceImpl 주입받아 사용)

	
	@ApiOperation(value = "관리자 페이지", notes = "API 설명 부분 : 사용자 정보 조회")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 404, message = "404 에러 발생"),
		@ApiResponse(code = 500, message = "500 에러 발생")
	})
	
	@GetMapping
	public ResponseEntity<APIResponse<?>> getAlluserInfo(@RequestHeader String userId, 
														@RequestParam(value = "index") int index){
		System.out.println("USER 아이디는 ?? " +userId);
		if("admin".equals(userId)) {
			AlluserInfoResponseDto ReponseData = userService.getAlluserInfo(index);
			return ResponseEntity.ok(APIResponse.success(ReponseData));
		}
		else {
			return ResponseEntity.ok(APIResponse.failbyValidation("권한이 없습니다."));
		}

	}

}
