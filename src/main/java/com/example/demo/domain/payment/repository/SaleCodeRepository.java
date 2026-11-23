package com.example.demo.domain.payment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.payment.entity.SaleCode;

@Repository 
public interface SaleCodeRepository extends JpaRepository<SaleCode, Long>{

    Optional<SaleCode> findByCode(String code);
}
