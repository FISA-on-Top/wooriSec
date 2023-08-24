package com.woori.domain.ipo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.woori.domain.entity.Ipo;

@Repository
public interface InquireRepository extends JpaRepository<Ipo, Long> {
	List<Ipo> findAll();

}
