package com.woori.contoller.mypage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woori.InvalidException;
import com.woori.dto.APIResponse;
import com.woori.dto.user.MypageDeleteRequestDto;
import com.woori.dto.user.MypageDeleteResponseDto;
import com.woori.dto.user.MypageInfoDto;
import com.woori.dto.user.MypageUpdateRequestDto;
import com.woori.service.user.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/userinfo")
public class MypageController {
	
	@Autowired
	UserService userService;
	
	@ApiOperation(value = "마이페이지", notes = "API 설명 부분 : 사용자 정보 조회, 수정, 탈퇴")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 404, message = "404 에러 발생"),
		@ApiResponse(code = 500, message = "500 에러 발생")
	})
	@GetMapping
	public ResponseEntity<APIResponse<?>> getUserInfo(@RequestHeader String userId){
		
		MypageInfoDto dto = userService.getUserInfoById(userId);
		
		if(dto == null) {
			return ResponseEntity.ok(APIResponse.failbyRequest("잘못된 요청입니다."));
		}
		return ResponseEntity.ok(APIResponse.success(dto));
	}

	@PostMapping("/modify")
	public ResponseEntity<APIResponse<?>> updateUserInfo(@RequestHeader(name = "userId")String userId, 
														@RequestBody MypageUpdateRequestDto requestBody){
		
		
		System.out.println("요청 Body 확인하자 "+ requestBody.toString());
		MypageInfoDto dto = userService.updateUserInfoById(userId , requestBody);
		
		if(dto == null) {
			return ResponseEntity.ok(APIResponse.failbyValidation("비밀번호 오류"));
		}
		
		return ResponseEntity.ok(APIResponse.success(dto));
	}
	
	@DeleteMapping("/withdrawal")
	public ResponseEntity<APIResponse<?>> deleteUserInfo(@RequestHeader(name="userId")String userId,
														@RequestBody MypageDeleteRequestDto requestBody){
	    try {
	        MypageDeleteResponseDto dto = userService.deleteUserInfoById(userId, requestBody);
	        return ResponseEntity.ok(APIResponse.success(dto));
	    }catch (InvalidException ie) {
	        switch (ie.getResultCode()) {
            case 1001:
                return ResponseEntity.ok(APIResponse.failbyValidation("인증에 실패하였습니다."));
            case 1003:
                return ResponseEntity.ok(APIResponse.failbyRequest("유효한 id가 아닙니다."));
            case 1004:
                return ResponseEntity.ok(APIResponse.failbyTransaction("탈퇴 작업 실패, 재시도하세요"));
            default:
                return ResponseEntity.ok(APIResponse.failbyRequest("Unknown error occurred."));
        }
	    }
	}
}
