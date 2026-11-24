package com.example.demo.settlement.service;

import org.springframework.stereotype.Service;

import com.example.demo.module.settlement.repository.SettlementRepository;
import com.example.demo.module.settlement.utils.SettlementCalculator;

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
