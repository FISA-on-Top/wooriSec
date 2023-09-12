package com.woori.contoller.ipo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.woori.InvalidException;
import com.woori.dto.APIResponse;
import com.woori.dto.ipo.CalenderResponseDto;
import com.woori.dto.ipo.IpoDetailDto;
import com.woori.dto.ipo.ListResponseDto;
import com.woori.service.ipo.IpoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController // json 형태 결과값 반환 -> @Response가 필요x
@RequestMapping("/ipo") // inquire의 API
public class IpoController {

    @Autowired
	private IpoService ipoService;	//@Service로 선언된 클래스의 인스턴스(ServiceImpl 주입받아 사용)

	@ApiOperation(value = "종목 조회", notes = "API 설명 부분 : ipo 종목 조회")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 404, message = "404 에러 발생"),
		@ApiResponse(code = 500, message = "500 에러 발생")
	})

	@GetMapping
	public ResponseEntity<APIResponse<?>> getIpoById(@RequestParam(name="ipoId") Long ipoId){
		try {
			IpoDetailDto dto = ipoService.getIpoById(ipoId);
			return ResponseEntity.ok(APIResponse.success(dto));		
		}catch(InvalidException ie) {
	        switch (ie.getResultCode()) {	
	        case 1003:
	        	 return ResponseEntity.ok(APIResponse.failbyRequest("잘못된 요청입니다."));
	        default:
	            return ResponseEntity.ok(APIResponse.failbyRequest("Unknown error occurred."));
	        }			
		}

	}
	  
	@GetMapping("/calendar")
	public ResponseEntity<APIResponse<?>> getIpoSummary(@RequestParam(name = "yyyy") int year, 
														@RequestParam(name = "mm") int month){
		
		try {
			List<CalenderResponseDto> dto = ipoService.getIpoSummary(year,month);
			return ResponseEntity.ok(APIResponse.success(dto));
			
		}catch (InvalidException ie) {
	        switch (ie.getResultCode()) {	
            default:
                return ResponseEntity.ok(APIResponse.failbyRequest("Unknown error occurred."));
	        }
		}
	}
	
	@GetMapping("/list")
	public ResponseEntity<APIResponse<?>> getAllIpo(@RequestParam(name = "index") int index){
		
		try {
			ListResponseDto dto = ipoService.getAllIpo(index);
			return ResponseEntity.ok(APIResponse.success(dto));
		}catch (InvalidException ie) {
	        switch (ie.getResultCode()) {	
	        default:
	            return ResponseEntity.ok(APIResponse.failbyRequest("Unknown error occurred."));
	        }
		}
	}
	
}
