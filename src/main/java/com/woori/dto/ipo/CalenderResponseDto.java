package com.woori.dto.ipo;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CalenderResponseDto {
	private Long ipoId;
	private String corpName;
	private LocalDate sbd;
}
