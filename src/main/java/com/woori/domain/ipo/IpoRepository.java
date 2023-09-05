package com.woori.domain.ipo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.woori.domain.entity.Ipo;

@Repository
public interface IpoRepository extends JpaRepository<Ipo, Long> {
	//날짜에 따라 가능한 공모주 조회
	List<Ipo> findBySbd(LocalDate date);

	//Ipo findByIpoId(Ipo ipo);
	//Ipo findByIpoId(Long ipoId);
	

}
