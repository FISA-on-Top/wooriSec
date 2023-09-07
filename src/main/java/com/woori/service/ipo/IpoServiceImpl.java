package com.woori.service.ipo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woori.InvalidException;
import com.woori.domain.entity.Ipo;
import com.woori.domain.ipo.IpoRepository;
import com.woori.dto.ipo.CalenderResponseDto;
import com.woori.dto.ipo.IpoDetailResponseDto;

@Service
public class IpoServiceImpl implements IpoService{
    @Autowired
    private IpoRepository ipoRepository;

    public List<IpoDetailResponseDto> getAllIpoDetails() {
        List<Ipo> ipos = ipoRepository.findAll();
   
        return ipos.stream().map(ipo -> {
            return new IpoDetailResponseDto(
            	ipo.getIpoId(),
            	ipo.getCorpCode(),
            	ipo.getCorpName(),
            	ipo.getSbd(),
            	ipo.getPymd(),
            	ipo.getRefund(),
            	ipo.getIpoDate(),
                ipo.getSlprc(),
                ipo.getCorpCls(),
                ipo.getStkcnt(),
                ipo.getCapitalIncrease()
            );
        }).collect(Collectors.toList());
    }
    
    public List<CalenderResponseDto> getIpoSummary(int year, int month) throws InvalidException {
    	
    	List<Ipo> ipos = ipoRepository.findBySbd(year, month);
    	
    	if(ipos.isEmpty())
    		return null;
    	
    	List<CalenderResponseDto> dtos = ipos.stream().map(ipo -> {
    		CalenderResponseDto dto = new CalenderResponseDto();
    		dto.setIpoId(ipo.getIpoId());
    		dto.setCorpName(ipo.getCorpName());
    		dto.setSbd(ipo.getSbd());
    		return dto;
    	}).collect(Collectors.toList());
    	
    	return dtos;
    }
}
