package com.woori.service.order;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.woori.domain.account.AccountRepository;
import com.woori.domain.entity.Account;
import com.woori.domain.entity.Ipo;
import com.woori.domain.entity.Orders;
import com.woori.domain.entity.User;
import com.woori.domain.ipo.IpoRepository;
import com.woori.domain.order.OrderRepository;
import com.woori.domain.user.UserRepository;
import com.woori.dto.account.VerifyRequestDto;
import com.woori.dto.order.OrderAccountDto;
import com.woori.dto.order.OrderAccountVerifyDto;
import com.woori.dto.order.OrderCancelDto;
import com.woori.dto.order.OrderInfoDto;
import com.woori.dto.order.OrderListDto;
import com.woori.dto.order.OrderRequestDto;
import com.woori.dto.order.OrderableDto;

@Service
public class OrderServiceImpl implements OrderService {
	
	//@Autowired로 필요한 레포지토리 주입받음
    @Autowired
    private OrderRepository orderRepository;
//    
    @Autowired
    private IpoRepository ipoRepository;
//    
    @Autowired
    private UserRepository userRepository;
//    
    @Autowired
    private AccountRepository accountRepository;
    
    //일자별 주문가능한 종목 조회
    @Override
    public List<OrderableDto> getIposInfo(LocalDate date) {
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
    
    //유저아이디 입력시 계좌번호 리턴
	public OrderAccountDto getAccountByUserId(String userId) {
		User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Account not found for user id: " + userId));

        return new OrderAccountDto(user.getAccountNum());
		
	}

	//계좌에 맞는 비밀번호 입력시 청약계좌선택란에 정보 제공 - 수정중
	@Override
	public OrderAccountVerifyDto getOrderableInfo(VerifyRequestDto requestDto) {
        Account account = accountRepository.findByAccountNumAndAccountPw(requestDto.getAccountNum(), requestDto.getAccountPw());
        
        
        if (account == null) {
            return null;  // 계좌 정보가 없거나 비밀번호가 일치하지 않습니다.
        }

        Long ipoId = requestDto.getIpoId();
        Optional<Ipo> ipo = ipoRepository.findById(ipoId);

        
        OrderAccountVerifyDto responseDto = new OrderAccountVerifyDto();
        User balance = userRepository.findByBalance(responseDto.getBalance());
        responseDto.setIpoId(requestDto.getIpoId());
        responseDto.setBalance(responseDto.getBalance());
        responseDto.setOrderableAmount(responseDto.getOrderableAmount());
        responseDto.setSlprc(responseDto.getSlprc());

        return responseDto;
    }

	//유저 잔액조회
	
	//청약 정보 입력 > ‘다음’ 버튼 클릭
	@Override
	public OrderInfoDto setOrderInfo(OrderRequestDto orderRequestDto) {
		
		String userId = "유저임";
		User user = userRepository.findById(userId).orElse(null);
		Ipo ipo = ipoRepository.findById(orderRequestDto.getIpoId()).get();
		
		Orders orders = new Orders(orderRequestDto, userId);
		orders.setUser(user);
		orders.setIpo(ipo);
		
		//신청내역 저장 DB order테이블에 맞춰서
		orderRepository.save(orders);
		
//		userRepository.save(orderResponseDto);
//		Optional<Ipo> optionalUser = userRepository.findById()
		Optional<Ipo> optionalIpo = ipoRepository.findById(orders.getIpo().getIpoId());
		
		if(optionalIpo.isPresent()) {
	        Ipo ipoTemp = optionalIpo.get(); 
	        // OrderInfoDto 객체를 생성하고 값을 설정
	        OrderInfoDto orderInfoDto = new OrderInfoDto(); 
	        orderInfoDto.setIpoId(ipoTemp.getIpoId()); //IpoId
	        orderInfoDto.setName("Account_Name");//accountName 청약계좌 소유자 명 -- String
	        orderInfoDto.setCorpName(ipo.getCorpName()); //종목명
	        orderInfoDto.setOrderAmount(orderRequestDto.getOrderAmount());//청약 주수
	        orderInfoDto.setSlprc(ipoTemp.getSlprc());//공모가(확정 발행가) 
	        orderInfoDto.setDeposit(orderRequestDto.getDeposit()); //증거금(deposit) Orders테이블
	        orderInfoDto.setPhoneNum(orderRequestDto.getPhoneNum());
	        orderInfoDto.setRefund(ipoTemp.getRefund());//환불일 
	        orderInfoDto.setPymd(ipoTemp.getPymd());//납입일
	        
	        return orderInfoDto;
	    } else {
	        // 해당 IPO 정보가 없는 경우
	        throw new EntityNotFoundException("IPO with id " + orderRequestDto.getIpoId() + " not found.");
	    }
	}
	
	
	//청약 결과 조회/취소 - 신청결과조회 서비스
//	@Override
//	public OrderListDto getOrderList(String userId) {
//		
////		Optional<OrderListDto> orderList = Optional<OrderListDto>;
//		Optional<OrderListDto> orderList  = orderRepository.findById(userId);
//		//return orderRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Order not found for user ID: " + userId));
//		if(!orderList.isPresent()) {
//			new EntityNotFoundException("Order not found for user ID: " + userId);
//		}
//		
//		return orderList.get();
//	}
	
	
	//청약결과 조회/취소 - '실행'버튼 클릭
	@Override
	public OrderCancelDto getcancelOrder(String accountNum, String Pw) {
		
		return new OrderCancelDto();
	}
	
	@ExceptionHandler
	public String exceptServiceMsg(Exception e) {
		e.printStackTrace();
		return "Service 예외발생";
	}
	
}