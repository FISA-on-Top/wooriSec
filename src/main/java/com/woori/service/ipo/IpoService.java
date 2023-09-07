package com.woori.service.ipo;

import java.util.List;

import com.woori.InvalidException;
import com.woori.dto.ipo.CalenderResponseDto;
import com.woori.dto.ipo.IpoDetailResponseDto;

public interface IpoService {

    List<IpoDetailResponseDto> getAllIpoDetails();
    
    List<CalenderResponseDto> getIpoSummary(int year, int month) throws InvalidException;
}
