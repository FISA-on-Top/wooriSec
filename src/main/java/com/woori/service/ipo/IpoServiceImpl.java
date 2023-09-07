package com.woori.service.ipo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.woori.InvalidException;
import com.woori.domain.entity.Ipo;
import com.woori.domain.ipo.IpoRepository;
import com.woori.dto.ipo.CalenderResponseDto;
import com.woori.dto.ipo.IpoDetailDto;
import com.woori.dto.ipo.ListResponseDto;

@Service
public class IpoServiceImpl implements IpoService{
    @Autowired
    private IpoRepository ipoRepository;

//    public List<IpoDetailDto> getAllIpoDetails() {
//        List<Ipo> ipos = ipoRepository.findAll();
//   
//        return ipos.stream().map(ipo -> {
//            return new IpoDetailDto(
//            	ipo.getIpoId(),
//            	ipo.getCorpCode(),
//            	ipo.getCorpName(),
//            	ipo.getSbd(),
//            	ipo.getPymd(),
//            	ipo.getRefund(),
//            	ipo.getIpoDate(),
//                ipo.getSlprc(),
//                ipo.getCorpCls(),
//                ipo.getStkcnt(),
//                ipo.getCapitalIncrease()
//            );
//        }).collect(Collectors.toList());
//    }
//  
    
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
    
    public ListResponseDto getAllIpo(int index) throws InvalidException{
    	Pageable pageable = PageRequest.of(index - 1, 10);
    	
    	Page<Ipo> PageIpo = ipoRepository.findAllByOrderBySbdDesc(pageable);
    	
    	List<IpoDetailDto> ipos = PageIpo.getContent().stream().map(ipo->{
    		return new IpoDetailDto(ipo);
    	}).collect(Collectors.toList());
    	
    	return ListResponseDto.responseData(PageIpo.getTotalPages(), PageIpo.getNumber()+1, ipos);
    }
    
    public IpoDetailDto getIpoById(Long ipoId) throws InvalidException{
    	
    	Optional<Ipo> OptionalIpo = ipoRepository.findById(ipoId);
    	
    	if(OptionalIpo.isEmpty())
    		throw new InvalidException("IpoId 오류",1003);
    	
    	return new IpoDetailDto(OptionalIpo.get());
    }
}
