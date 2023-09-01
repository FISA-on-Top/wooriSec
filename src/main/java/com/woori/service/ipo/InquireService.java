package com.woori.service.ipo;

import java.util.List;

import org.springframework.stereotype.Service;

import com.woori.dto.inquire.InquireDto;

public interface InquireService {

    List<InquireDto> getAllIpoDetails();
	}
