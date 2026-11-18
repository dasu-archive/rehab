package com.example.demo.domain.event.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    public Page<EventDto> searchEvent(String q, Pageable pageable){
        Page<Event> eventList = eventRepository.findByNameContaining(q, pageable);

        return new PageImpl<>(eventList.get().map(e -> new EventDto(e.getName(), e.getId())).toList(), pageable, eventList.getTotalElements());
    }

    @Transactional 
    public boolean addEvent(RequestAddEventDto dto){
        
        eventRepository.save(new Event(dto.eventName(), dto.capacity()));

        return true;
    }

    
    @Transactional 
    public boolean registrationEvent(RequestRegistrationEventDto dto){



            
        // 찾기
        Event event = eventRepository.findById(dto.eventId()).orElseThrow();
        Member member = memberRepository.findByName(dto.username()).orElseThrow();

        // 정원초과 확인
        if(event.getApplicantCount() >= event.getCapacity()) throw new CustomException(ErrorCode.CAPACITY_EXCEEDED);
        // 중복 신청 확인
        if(!applicantRepository.findByMemberAndEvent(member,event).isEmpty()) throw new CustomException(ErrorCode.INVALID_INPUT);
        
        // 등록
        applicantRepository.save(new Applicant(member, event));
        event.addApplicantCount();

        return true;
    }
}
