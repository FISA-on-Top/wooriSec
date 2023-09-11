package com.woori.dto.order;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrdersResponseDto {
	
	private final int totalPage;
	private final int currentPage;
	private final List<OrderableDto> ipoSummary;

	public static OrdersResponseDto responseData(int totalPage, int currentPage, List<OrderableDto> orderableDtos) {
		
		return new OrdersResponseDto(totalPage, currentPage,orderableDtos);
	}
}
