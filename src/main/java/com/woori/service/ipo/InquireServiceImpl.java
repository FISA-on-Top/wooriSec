package com.woori.service.ipo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woori.domain.entity.Ipo;
import com.woori.domain.ipo.InquireRepository;
import com.woori.dto.inquire.InquireDto;

	//캘린더 조회
	//완규님한테 (모든 데이터는 json 형식으로 보낼거니까 어떻게 받아올지 여쭤보기
@Service
public class InquireServiceImpl implements InquireService {

    @Autowired
    private InquireRepository inquireRepository;

    @Override
    public List<InquireDto> getAllIpoDetails() {
        List<Ipo> ipos = inquireRepository.findAll();

        return ipos.stream().map(ipo -> {
            return new InquireDto(
                ipo.getCorpCls(),
                ipo.getCorpName(),
                ipo.getCapitalIncrease(),
                ipo.getSbd(),
                ipo.getRefund(),
                ipo.getSlprc()
            );
        }).collect(Collectors.toList());
    }
}