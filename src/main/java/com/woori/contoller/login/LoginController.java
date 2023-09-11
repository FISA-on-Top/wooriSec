package com.woori.contoller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.woori.domain.entity.User;
import com.woori.dto.APIResponse;
import com.woori.dto.user.LoginRequestDto;
import com.woori.dto.user.LoginResponseDto;
import com.woori.service.user.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "사용자 로그인", notes = "API 설명 부분 : ID와 비밀번호를 통한 로그인 처리")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 404, message = "404 에러 : 사용자를 찾을 수 없음"),
		@ApiResponse(code = 500, message = "500 서버 오류 발생")
	})
	
	// login 처리 메소드
	@PostMapping("/loginauth")
	public ResponseEntity<APIResponse<?>> login(@RequestBody LoginRequestDto user) {
		
		User u = userService.login(user);
		
		
		if (u != null) {
			return ResponseEntity.ok(APIResponse.success(new LoginResponseDto("0000", "ok", u.getUserId())));
		}
		
		return ResponseEntity.ok(APIResponse.failbyRequest("Authentication failed."));

	}
}
