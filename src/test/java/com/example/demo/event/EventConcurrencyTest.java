package com.example.demo.event;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest 
@ActiveProfiles ("test")
@Slf4j
public class EventConcurrencyTest {

    static final int CAPACITY = 100;
    static final int REQUESTS = 1000;

    @Autowired EventService eventService;
    @Autowired EventRepository eventRepository;
    @Autowired MemberRepository memberRepository;
    @Autowired ApplicantRepository applicantRepository;

    Long eventId;
    List<String> memberNames;

    @BeforeEach 
    void 정원_100명행사_회원_1000명_저장(){
        eventId = eventRepository.save(new Event("동시성 테스트", CAPACITY)).getId();

        List<Member> members = new ArrayList<>();
        for(int i =0 ; i < REQUESTS; i++){
            members.add(new Member("member"+i));
        }

        memberRepository.saveAll(members);
        memberNames = members.stream().map(Member::getName).toList();
    }

    @AfterEach
    void 정리() {                                  // 롤백이 없으니 직접 삭제. FK 때문에 신청 → 행사 → 회원 순서
        applicantRepository.deleteAllInBatch();
        eventRepository.deleteAllInBatch();
        memberRepository.deleteAllInBatch();
    }

    @Test 
    void 정원_100명에_1000명이_동시에_신청하면_100명만_성공() throws InterruptedException{
        ExecutorService pool = Executors.newFixedThreadPool(32);
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(REQUESTS);
        AtomicInteger success = new AtomicInteger();
        AtomicInteger rejected = new AtomicInteger();
        Queue<Throwable> unexpected = new ConcurrentLinkedQueue<>();

        for(String name: memberNames){
            pool.submit(()->{
                try{
                    start.await();
                    eventService.registrationEvent(new RequestRegistrationEventDto(eventId, name));
                    success.incrementAndGet();
                }catch(CustomException e){
                    rejected.incrementAndGet();
                }catch(Throwable e){
                    unexpected.add(e);
                }finally{
                    done.countDown();
                }
            });
        }

        start.countDown();
        boolean finished = done.await(60, TimeUnit.SECONDS);
        pool.shutdown();

        Event event = eventRepository.findById(eventId).orElseThrow();
        long applicantRows = applicantRepository.count();

        log.info("성공 {} / 거절 {} / 예상 못 한 예외 {} / applicantCount {} / 신청 row {} ",
            success.get(),
            rejected.get(),
            unexpected.size(),
            event.getApplicantCount(),
            applicantRows
        );

        unexpected.stream().map(e -> e.getClass().getSimpleName()).distinct().forEach(type->log.info("예외종류{}", type));

        assertThat(finished).as("60안에 전부 끝남").isTrue();
        assertThat(unexpected).as("예상 못 한 예외").isEmpty();
        assertThat(success.get()).as("성공 수").isEqualTo(CAPACITY);
        assertThat(event.getApplicantCount()).as("applicantCount").isEqualTo(CAPACITY);
        assertThat(applicantRows).as("신청 수").isEqualTo(CAPACITY);
    }
    
}
