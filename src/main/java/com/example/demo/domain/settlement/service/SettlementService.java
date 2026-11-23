package com.example.demo.domain.settlement.service;

import org.springframework.stereotype.Service;

import com.example.demo.domain.settlement.repository.SettlementRepository;
import com.example.demo.domain.settlement.utils.SettlementCalculator;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class SettlementService {

    private final SettlementRepository settlementRepository;
    private final SettlementCalculator settlementCalculator;


    public void create(){
        settlementCalculator.calculate();
    }
    
}
