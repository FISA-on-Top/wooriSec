package com.woori.service.ipo;

import java.util.List;

import com.woori.InvalidException;
import com.woori.dto.ipo.CalenderResponseDto;
import com.woori.dto.ipo.IpoDetailDto;
import com.woori.dto.ipo.ListResponseDto;

public interface IpoService {

   // List<IpoDetailDto> getAllIpoDetails();
	
    public List<CalenderResponseDto> getIpoSummary(int year, int month) throws InvalidException;
    
    public ListResponseDto getAllIpo(int index) throws InvalidException;
    
    public IpoDetailDto getIpoById(Long ipoId) throws InvalidException;
}
