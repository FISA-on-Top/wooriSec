package com.woori.dto.ipo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ListResponseDto {
	
	private final int totalPage;
	private final int currentPage;
	private final List<IpoDetailDto> ipo;
	
	public static ListResponseDto responseData(int totalPage, int currentPage, List<IpoDetailDto> dtos) {
		
		return new ListResponseDto(totalPage, currentPage,dtos);
	}

}
