package com.woori.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woori.domain.HelloRepository;
import com.woori.domain.entity.Hello;

@RestController
public class HelloController {
	//repo 연결 해줘용
	@Autowired
	private HelloRepository dao;
	
	@GetMapping("/hello")
	public Iterable<Hello> getHelloAll() {
		return dao.findAll();
		
	}

}
