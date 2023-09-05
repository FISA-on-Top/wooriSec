package com.woori.contoller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
	@PostMapping("loginauth")
	public LoginResponseDto login(LoginRequestDto user) {

		User u = userService.login(user);
		
		//null이 아닌 경우 id 값을 json 형식으로 응답 
		if (u != null) {
			return new LoginResponseDto("0000", "ok", u.getUserId());
		}

		return new LoginResponseDto("1001", "fail", null);

	}
}
