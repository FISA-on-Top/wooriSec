package com.woori.service.user;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.woori.domain.entity.User;
import com.woori.domain.user.UserRepository;
import com.woori.dto.user.AlluserInfoResponseDto;
import com.woori.dto.user.LoginRequestDto;
import com.woori.dto.user.MypageInfoDto;
import com.woori.dto.user.UserInfoDto;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
    /**
     *  로그인 기능
     *  화면에서 LoginRequest(loginId, password)을 입력받아 loginId와 password가 일치하면 User return
     *  loginId가 존재하지 않거나 password가 일치하지 않으면 null return
     */
    public User login(LoginRequestDto user) {
        Optional<User> optionalUser = userRepository.findByUserIdAndUserPw(user.getUserId(), user.getUserPw());

        // loginId와 일치하는 User가 없으면 null return
        if(optionalUser.isEmpty()) {
            return null;
        }

       return optionalUser.get();

    }
    
    public AlluserInfoResponseDto getAlluserInfo(int index){
    	Pageable pageable = PageRequest.of(index - 1, 10);
    	
    	Page<User> pageUser = userRepository.findAll(pageable);
    	
    	System.out.println(pageUser.getContent());
    	
    	List<UserInfoDto> dtos =pageUser.getContent().stream()
    			.map(user ->{
    				UserInfoDto dto = new UserInfoDto();
    				dto.setUserName(user.getUserName());
    				dto.setBirth(user.getBirth());
    				dto.setUserId(user.getUserId());
    				dto.setAccountNum(user.getAccountNum());
    				dto.setCreatedAt(user.getCreatedAt());
    				
    				return dto;
    			}).collect(Collectors.toList());
    	
    	return AlluserInfoResponseDto.responseData(pageUser.getTotalPages(), pageUser.getNumber()+1, dtos);
    }
    
    public MypageInfoDto getUserInfoById(String id) {
    	Optional<User> optionalUser = userRepository.findById(id);
   
    	
    	if(!optionalUser.isPresent()) {
    		return null;
    	}
    	else {
    	   	MypageInfoDto dto = new MypageInfoDto();
    	   	User user = optionalUser.get();
    	   	dto.setUserName(user.getUserName());
    	   	dto.setUserId(user.getUserId());
    	   	dto.setBirth(user.getBirth());
    	   	dto.setPhoneNum(user.getPhoneNum());
    	   	dto.setEmail(user.getEmail());
    	   	dto.setAccountNum(user.getAccountNum());
    		return dto;
    	}
    }
    /**
     * userId(Long)를 입력받아 User을 return 해주는 기능
     * 인증, 인가 시 사용
     * userId가 null이거나(로그인 X) userId로 찾아온 User가 없으면 null return
     * userId로 찾아온 User가 존재하면 User return
     */
	/*
	 * public User getLoginUserById(String userId) { if(userId == null) return null;
	 * 
	 * Optional<User> optionalUser = userRepository.findById(userId);
	 * if(optionalUser.isEmpty()) return null;
	 * 
	 * return optionalUser.get(); }
	 */

    /**
     * loginId(String)를 입력받아 User을 return 해주는 기능
     * 인증, 인가 시 사용
     * loginId가 null이거나(로그인 X) userId로 찾아온 User가 없으면 null return
     * loginId로 찾아온 User가 존재하면 User return
     */
//    public User getLoginUserByLoginId(String loginId) {
//        if(loginId == null) return null;
//
//        Optional<User> optionalUser = userRepository.findByLoginId(loginId);
//        if(optionalUser.isEmpty()) return null;
//
//        return optionalUser.get();
//    }
}
