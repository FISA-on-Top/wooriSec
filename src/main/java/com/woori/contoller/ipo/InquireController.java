package com.woori.contoller.ipo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woori.dto.inquire.InquireDto;
import com.woori.service.ipo.InquireServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController // json 형태 결과값 반환 -> @Response가 필요x
@RequestMapping("/ipo") // inquire의 API
public class InquireController {

    @Autowired
	private InquireServiceImpl inquireService; // ??이거 모르겠음

	@ApiOperation(value = "종목 조회", notes = "API 설명 부분 : ipo 종목 조회")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "OK !!"),
		@ApiResponse(code = 404, message = "404 에러 발생시 출력 메세지, Not Found !"),
		@ApiResponse(code = 500, message = "500 에러 발생시 출력 메세지, 가령 Internal Server Error !")
	})
	@GetMapping("/list") // 팝업창으로 떠야할수도
	public ResponseEntity<List<InquireDto>> getAllIpoDetails() {
		List<InquireDto> inquireDtos = inquireService.getAllIpoDetails();
		return ResponseEntity.ok(inquireDtos);
	}
}
