package com.woori.domain.ipo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.woori.domain.entity.Ipo;

@Repository
public interface IpoRepository extends JpaRepository<Ipo, Long> {
//	List<Ipo> findAll(); JPA기본기능
	//날짜에 따라 가능한 공모주 조회
	List<Ipo> findBySbd(Date date);


}
