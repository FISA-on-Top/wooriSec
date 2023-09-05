package com.woori.service.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woori.domain.entity.User;
import com.woori.domain.user.UserRepository;
import com.woori.dto.user.LoginRequestDto;

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
