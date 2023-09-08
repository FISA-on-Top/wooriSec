package com.woori.domain.ipo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.woori.domain.entity.Ipo;

@Repository
public interface IpoRepository extends JpaRepository<Ipo, Long> {
	//날짜에 따라 가능한 공모주 조회
	List<Ipo> findBySbd(LocalDate date);
	
	Page<Ipo> findBySbd(LocalDate sbd, Pageable pageable);
	
	//년, 월에 따라 공모주 조회
//    @Query("SELECT i FROM Ipo i WHERE YEAR(i.sbd) = :year AND MONTH(i.sbd) = :month")
//    List<Ipo> findBySbd(@Param("year") int year, @Param("month") int month);
    @Query(value = "SELECT * FROM ipo WHERE YEAR(sbd) = :year AND MONTH(sbd) = :month", nativeQuery = true)
    List<Ipo> findBySbd(@Param("year") int year, @Param("month") int month);

    //sdb 최신일 기준 내림차순 공모주 종목조회
    Page<Ipo> findAllByOrderBySbdDesc(Pageable pageable);
    
}
