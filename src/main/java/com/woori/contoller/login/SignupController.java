package com.woori.contoller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woori.dto.APIResponse;
import com.woori.dto.user.SignupAccountRequestDto;
import com.woori.dto.user.SignupAccountResponseDto;
import com.woori.service.account.AccountService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/signup")
public class SignupController {
	
	@Autowired
	private AccountService accountService;
	
	@PostMapping("/account")
	public ResponseEntity<APIResponse<?>> verifyAccount(
			@RequestBody SignupAccountRequestDto requestDto) {
		SignupAccountResponseDto responseDto = accountService.verifyAccount(requestDto);
		
		if (requestDto != null) {
            return ResponseEntity.ok(APIResponse.success(responseDto));
        } else {
        	return ResponseEntity.ok(APIResponse.failbyRequest("비밀번호가 일치하지 않습니다"));
        	//return ResponseEntity.badRequest().body("비밀번호가 일치하지 않습니다");
        }
		
	}

}
