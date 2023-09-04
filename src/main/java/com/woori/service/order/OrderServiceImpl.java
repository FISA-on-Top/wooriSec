package com.woori.service.order;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woori.domain.entity.Ipo;
import com.woori.domain.entity.User;
import com.woori.domain.ipo.IpoRepository;
import com.woori.domain.user.UserRepository;
import com.woori.dto.order.OrderAccountDto;
import com.woori.dto.order.OrderInfoDto;
import com.woori.dto.order.OrderListDto;
import com.woori.dto.order.OrderableDto;

@Service
public class OrderServiceImpl implements OrderService {
	
	//@Autowired로 필요한 레포지토리 주입받음
//    @Autowired
//    private OrderRepository orderRepository;
//    
    @Autowired
    private IpoRepository ipoRepository;
//    
    @Autowired
    private UserRepository userRepository;
//    
//    @Autowired
//    private AccountRepository accountRepository;
    
    //일자별 주문가능한 종목 조회
    @Override
    public List<OrderableDto> getIposInfo(Date date) {
    	List<Ipo> ipos = ipoRepository.findBySbd(date);

        return ipos.stream().map(ipo -> {
            OrderableDto dto = new OrderableDto();
            dto.setIpoId(ipo.getIpoId());
            dto.setCorpCls(ipo.getCorpCls());
            dto.setCorpName(ipo.getCorpName());
            dto.setSbd(ipo.getSbd());
            dto.setRefund(ipo.getRefund());
            dto.setSlprc(ipo.getSlprc());

            return dto;
        }).collect(Collectors.toList());
    }
    
	public OrderAccountDto getAccountByUserId(String userId) {
		User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Account not found for user id: " + userId));

        return new OrderAccountDto(user.getAccountNum());
		
	}
	
	//청약 정보 입력 > ‘다음’ 버튼 클릭
	@Override
	public OrderInfoDto getOrderInfo() {
		return null;
	}
	
	
	//청약 결과 조회/취소 - 신청결과조회 서비스
	@Override
	public OrderListDto getOrderList(String userId) {
		
		//임시로 null반환
		return null;
	}
	
	
}