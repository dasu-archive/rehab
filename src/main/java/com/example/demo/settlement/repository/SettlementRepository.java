package com.example.demo.settlement.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.module.settlement.entity.Settlement;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository 
public interface SettlementRepository extends JpaRepository<Settlement, Long>{
    
}
