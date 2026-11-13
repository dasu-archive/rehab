package com.example.demo.domain.event.service;


import org.springframework.stereotype.Service;

import com.example.demo.config.exception.CustomException;
import com.example.demo.config.exception.ErrorCode;
import com.example.demo.domain.event.dto.request.RequestAddEventDto;
import com.example.demo.domain.event.dto.request.RequestRegistrationEventDto;
import com.example.demo.domain.event.dto.response.EventDto;
import com.example.demo.domain.event.entity.Applicant;
import com.example.demo.domain.event.entity.Event;
import com.example.demo.domain.event.repository.ApplicantRepository;
import com.example.demo.domain.event.repository.EventRepository;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class EventService {
    
    private final EventRepository eventRepository;
    private final MemberRepository memberRepository;
    private final ApplicantRepository applicantRepository;



    
    public EventDto getEvent(Long id){
        Event event = eventRepository.findById(id).orElseThrow(()->new CustomException(ErrorCode.DATA_NOT_FOUND));
        return event.toEventDto();
    }

    public void searchEvent(String q){
        // eventRepository.findByName(q);
    }

    @Transactional 
    public boolean addEvent(RequestAddEventDto dto){
        
        eventRepository.save(new Event(dto.getEventName(), dto.getCapacity()));

        return true;
    }

    
    @Transactional 
    public boolean registrationEvent(RequestRegistrationEventDto dto, Long userId){

        try{

            // 정원초과 확인
            
            // 찾기
            Event event = eventRepository.findById(dto.getEventId()).orElseThrow();
            Member member = memberRepository.findById(userId).orElseThrow();

            // 정원초과 확인
            if(event.getApplicantCount() >= event.getCapacity()) throw new Exception();
            // 중복 신청 확인
            Applicant applicant = applicantRepository.findByMemberAndEvent(member,event).orElse(new Applicant(member,event));
            
            // 등록
            applicantRepository.save(applicant);


        }catch(Exception e){

        }
        return true;
    }
}
