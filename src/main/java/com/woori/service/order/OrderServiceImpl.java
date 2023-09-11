package com.woori.service.order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import com.woori.dto.order.OrderApprovalRequestDto;
import com.woori.dto.order.OrderCancelDto;
import com.woori.dto.order.OrderInfoDto;
import com.woori.dto.order.OrderListDto;
import com.woori.dto.order.OrderableDto;
import com.woori.dto.order.OrdersResponseDto;

@Service
public class OrderServiceImpl implements OrderService {
	
	//취소 수수료
	private static final BigDecimal CANCEL_FEE = new BigDecimal("2000");

	
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
    public OrdersResponseDto getIposInfo(LocalDate date, int index) {
    	Pageable pageable = PageRequest.of(index - 1, 10);
    	
    	Page<Ipo> pageIpo = ipoRepository.findBySbd(date,pageable);
    	
    	List<OrderableDto> ipos = pageIpo.getContent().stream().map(ipo -> {
    	    OrderableDto dto = new OrderableDto();
    	    dto.setIpoId(ipo.getIpoId());
    	    dto.setCorpCls(ipo.getCorpCls());
    	    dto.setCorpName(ipo.getCorpName());
    	    dto.setSbd(ipo.getSbd());
    	    dto.setRefund(ipo.getRefund());
    	    dto.setSlprc(ipo.getSlprc());

    	    return dto;
    	}).collect(Collectors.toList());
    	
    	return OrdersResponseDto.responseData(pageIpo.getTotalPages(), pageIpo.getNumber()+1, ipos);
    }
    
    //유저아이디 입력시 계좌번호 리턴
	public OrderAccountDto getAccountByUserId(String userId) {
		User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Account not found for user id: " + userId));

        return new OrderAccountDto(user.getAccount().getAccountNum());
		
	}

	//계좌에 맞는 비밀번호 입력시 청약계좌선택란에 정보 제공
	@Override
	public OrderAccountVerifyDto getOrderableInfo(VerifyRequestDto requestDto) {
        Optional<Account> account = accountRepository.findByAccountNumAndAccountPw(requestDto.getAccountNum(), requestDto.getAccountPw());
        
        
        if (account == null) {
            return null;  // 계좌 정보가 없거나 비밀번호가 일치하지 않습니다.
        }

        //Ipo 정보 조회
        Optional<Ipo> optionalIpo = ipoRepository.findById(requestDto.getIpoId());
        Ipo ipo = optionalIpo.get();


        BigDecimal balance = account.get().getBalance();
        Long stkcnt = ipo.getStkcnt();
        int orderableAmount = (int) (stkcnt * 0.1);	     // OrderableAmount 계산: stkcnt*0.1

        OrderAccountVerifyDto responseDto = new OrderAccountVerifyDto();
        responseDto.setIpoId(requestDto.getIpoId());
        responseDto.setBalance(balance);
        responseDto.setOrderableAmount(orderableAmount);
        responseDto.setSlprc(ipo.getSlprc());

        
        return responseDto;
    }
	
	//청약 정보 입력 > ‘다음’ 버튼 클릭
	@Override
	public OrderInfoDto setOrderInfo(OrderApprovalRequestDto orderApprovalRequestDto) {
		
		
		User user = userRepository.findByPhoneNum(orderApprovalRequestDto.getPhoneNum())
				.orElseThrow(() -> new RuntimeException("User not found with phone number: " + orderApprovalRequestDto.getPhoneNum()));
		Ipo ipo = ipoRepository.findById(orderApprovalRequestDto.getIpoId()).orElse(null);
		
		Orders orders = new Orders(orderApprovalRequestDto);
		System.out.println(user);
		orders.setUser(user);
		orders.setIpo(ipo);
		orders.setOrderAmount(orderApprovalRequestDto.getOrderAmount());
		orders.setOrderableAmount((new BigDecimal(ipo.getStkcnt()).multiply(new BigDecimal("0.1"))).intValue());//신청내역 저장 DB order테이블에 맞춰서
		orders.setStatus("Ordered");		
		orders.setOrderDate(LocalDateTime.now());
//		orders.setcancelDate(null);
		orders.setPhoneNum(orderApprovalRequestDto.getPhoneNum());
		orders.setDeposit(orderApprovalRequestDto.getDeposit());
		orderRepository.save(orders);
		
//		userRepository.save(orderResponseDto);
//		Optional<Ipo> optionalUser = userRepository.findById()
		Optional<Ipo> optionalIpo = ipoRepository.findById(orders.getIpo().getIpoId());
		
		if(optionalIpo.isPresent()) {
	        Ipo ipoTemp = optionalIpo.get(); 
	        // OrderInfoDto 객체를 생성하고 값을 설정
	        OrderInfoDto orderInfoDto = new OrderInfoDto(); 
	        orderInfoDto.setIpoId(ipoTemp.getIpoId()); //IpoId
	        orderInfoDto.setUserName(user.getUserName());//accountName 청약계좌 소유자 명 -- String
	        orderInfoDto.setCorpName(ipo.getCorpName()); //종목명
	        orderInfoDto.setOrderAmount(orderApprovalRequestDto.getOrderAmount());//청약 주수
	        orderInfoDto.setSlprc(ipoTemp.getSlprc());//공모가(확정 발행가) 
	        orderInfoDto.setDeposit(orderApprovalRequestDto.getDeposit()); //증거금(deposit) Orders테이블
	        orderInfoDto.setPhoneNum(orderApprovalRequestDto.getPhoneNum());
	        orderInfoDto.setRefund(ipoTemp.getRefund());//환불일 
	        orderInfoDto.setPymd(ipoTemp.getPymd());//납입일
	        
	        return orderInfoDto;
	    } else {
	        // 해당 IPO 정보가 없는 경우
	        throw new EntityNotFoundException("IPO with id " + orderApprovalRequestDto.getIpoId() + " not found.");
	    }
	}
	
	
	//청약 결과 조회/취소 - 신청결과조회 서비스
	@Override
	public List<OrderListDto> getOrderList(String userId, LocalDate date) {
		
		List<Orders> orders = orderRepository.findByUserIdAndOrderDate(userId, date);
		
		if(orders == null || orders.isEmpty()) {
			return new ArrayList<>();
		}
		
		return orders.stream().map(order -> {
			Ipo ipo = order.getIpo();
			OrderListDto orderListDto = new OrderListDto();
			orderListDto.setStatus(order.getStatus()); //status
			orderListDto.setOrderAmount(order.getOrderAmount()); //orderAmount
			orderListDto.setCorpCls(ipo.getCorpCls());//corpCls
			//subscriptionClassification "상장" 프론트처리
			orderListDto.setDeposit(order.getDeposit());//deposit
			//commission = 2000 고정
			orderListDto.setOrderId(order.getOrderId());//orderId
			orderListDto.setOrderDate(order.getOrderDate());//orderDate
			orderListDto.setCorpCode(ipo.getCorpCode());//corpCode
			orderListDto.setCorpName(ipo.getCorpName());//corpName
			orderListDto.setSbd(date);
			orderListDto.getRefund();
			return orderListDto;
			
		}).collect(Collectors.toList());
	}

	
	//청약결과 조회/취소 - '실행'버튼 클릭
	@Override
	@Transactional
	public OrderCancelDto getCancelOrder(String accountNum, String accountPw, Long orderId) {
		// 1. 계좌 정보 조회
        Account account = accountRepository.findByAccountNumAndAccountPw(accountNum, accountPw)
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));

        // 2. 주문 정보 조회
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        
        if(order.getCancelDate() != null) {
        	throw new IllegalArgumentException("주문이 이미 취소되었습니다.");
        }
        // 3. 청약 취소 및 잔고 계산
        BigDecimal updatedBalance = account.getBalance().add(order.getDeposit().subtract(CANCEL_FEE));
        account.setBalance(updatedBalance);
        accountRepository.save(account);

        // 4. 주문 취소 날짜 업데이트
        order.setCancelDate(LocalDateTime.now());
        order.setStatus("Cancelled");
        orderRepository.save(order);
        
		
		OrderCancelDto orderCancelDto = new OrderCancelDto();
        orderCancelDto.setBalance(updatedBalance);
        orderCancelDto.setOrderAmount(order.getOrderAmount());
        orderCancelDto.setCorpCls(order.getIpo().getCorpCls());
        orderCancelDto.setDeposit(order.getDeposit());
        orderCancelDto.setOrderId(order.getOrderId());
        orderCancelDto.setCancelDate(order.getCancelDate());
        orderCancelDto.setCorpCode(order.getIpo().getCorpCode());
        orderCancelDto.setCorpName(order.getIpo().getCorpName());
		
		return orderCancelDto;
	}
	
	@ExceptionHandler
	public String exceptServiceMsg(Exception e) {
		e.printStackTrace();
		return "Service 예외발생";
	}

	
}