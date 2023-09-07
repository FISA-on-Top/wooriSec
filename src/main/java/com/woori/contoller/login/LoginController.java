package com.woori.contoller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.woori.domain.entity.User;
import com.woori.dto.user.LoginRequestDto;
import com.woori.dto.user.LoginResponseDto;
import com.woori.service.user.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginController {

	@Autowired
	private UserService userService;

	// login 처리 메소드
	@PostMapping("/loginauth")
	public LoginResponseDto login(@RequestBody LoginRequestDto user) {
		
		User u = userService.login(user);
		
		//null이 아닌 경우 id 값을 json 형식으로 응답
		//수정 필요!!!! 형식 맞춰 수정!! 수정후 주석 지우기
		if (u != null) {
			return new LoginResponseDto("0000", "ok", u.getUserId());
		}

		return new LoginResponseDto("1001", "fail", null);

	}
}
