package com.example.demo.event;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.domain.event.entity.Event;
import com.example.demo.domain.event.repository.ApplicantRepository;
import com.example.demo.domain.event.repository.EventRepository;
import com.example.demo.domain.event.service.EventService;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest 
@ActiveProfiles ("test")
@Slf4j 
@Transactional 
public class ExceedCapacityTest {
    
    @Autowired EventService eventService;
    @Autowired EventRepository eventRepository;
    @Autowired MemberRepository memberRepository;
    @Autowired ApplicantRepository applicantRepository;
    @Autowired EntityManagerFactory emf;
    @Autowired EntityManager em;
    Statistics stats;

    @BeforeEach 
    void 행사_10개_저장(){
            for (int i = 0; i < 10; i++) {
        Member host = memberRepository.save(new Member("host" + i));
            eventRepository.save(new Event("행사" + i, 5, host));
        }
        em.flush();
        em.clear();

        stats = emf.unwrap(SessionFactory.class).getStatistics();
        stats.setStatisticsEnabled(true);
        stats.clear(); 
    }

    @Test 
    void FetchJoin적용(){
        List<Event> fetchJoin = eventRepository.findAllFetchJoin();
        for(Event event: fetchJoin){
            System.out.println(event.getHost().getName());
        }
        long sqlCount = stats.getPrepareStatementCount();


        log.info("FetchJoin 행사 10건 목록 조회: SQL {}개, host 따로 조회 {}건", sqlCount, stats.getEntityFetchCount());
        
        stats.clear();
    }

    @Test 
    void EntityGraph적용(){
        
    List<Event> entityGraph = eventRepository.findAllWithEntityGraphBy();
    for(Event event: entityGraph){
        System.out.println(event.getHost().getName());
    }
    long sqlCount = stats.getPrepareStatementCount();
    log.info("EntityGraph 행사 10건 목록 조회: SQL {}개, host 따로 조회 {}건", sqlCount, stats.getEntityFetchCount());
    
    }

}
