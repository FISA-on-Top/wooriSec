package com.woori.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.woori.domain.entity.Hello;

@Repository
public interface HelloRepository extends JpaRepository<Hello, Integer>{
}