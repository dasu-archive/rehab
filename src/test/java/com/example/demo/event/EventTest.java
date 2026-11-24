package com.example.demo.event;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.event.dto.request.RequestRegistrationEventDto;
import com.example.demo.event.entity.Event;
import com.example.demo.event.repository.ApplicantRepository;
import com.example.demo.event.repository.EventRepository;
import com.example.demo.event.service.EventService;
import com.example.demo.global.config.exception.CustomException;
import com.example.demo.global.config.exception.ErrorCode;
import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest 
@Transactional 
@Slf4j 
@ActiveProfiles("test")
public class EventTest {
    
    @Autowired EventService eventService;
    @Autowired EventRepository eventRepository;
    @Autowired MemberRepository memberRepository;
    @Autowired ApplicantRepository applicantRepository;
    @Autowired EntityManagerFactory emf;
    @Autowired EntityManager em;


    @Test 
    void 정원_초과_예외(){
        Member first = memberRepository.save(new Member("first"));
        Member second = memberRepository.save(new Member("second"));
        Member third = memberRepository.save(new Member("third"));
        Member fourth = memberRepository.save(new Member("fourth"));

        Event event = eventRepository.save(new Event("테스트", 3));
        eventService.registrationEvent(new RequestRegistrationEventDto(event.getId(),first.getName()));
        eventService.registrationEvent(new RequestRegistrationEventDto(event.getId(),second.getName()));
        eventService.registrationEvent(new RequestRegistrationEventDto(event.getId(),third.getName()));
        

        assertThatThrownBy(() -> eventService.registrationEvent(new RequestRegistrationEventDto(event.getId(),fourth.getName())))
            .isInstanceOf(CustomException.class)
            .extracting("errorCode")
            .isEqualTo(ErrorCode.CAPACITY_EXCEEDED);

        Event found = eventRepository.findById(event.getId()).orElseThrow();
        assertThat(found.getApplicantCount()).isEqualTo(3);
            
    }
    
    /*
    N+1 재현: 행사 10개 + host 조회. Hibernate Statistics로 쿼리 수 측정
    */
    @Test 
    void Nplus1재현(){
        List<Member> memberList = new ArrayList<>();
        for(int i=0; i< 10; i++){
            memberList.add(memberRepository.save(new Member(""+i)));
        }
        for(Member member:memberList){
            eventRepository.save(new Event(member.getName(),5,member));
        }  

        em.flush();
        em.clear();

        Statistics stats = emf.unwrap(SessionFactory.class).getStatistics();
        stats.setStatisticsEnabled(true);

        stats.clear();
        List<Event> fetchJoin = eventRepository.findAllFetchJoin();
        for(Event event: fetchJoin){
            System.out.println(event.getHost().getName());
        }
        long sqlCount = stats.getPrepareStatementCount();


        log.info("FetchJoin 행사 10건 목록 조회: SQL {}개, host 따로 조회 {}건", sqlCount, stats.getEntityFetchCount());
 
    }
}
