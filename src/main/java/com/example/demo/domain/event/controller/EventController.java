package com.example.demo.domain.event.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.response.ResponseDto;
import com.example.demo.domain.event.dto.request.RequestAddEventDto;
import com.example.demo.domain.event.dto.request.RequestRegistrationEventDto;
import com.example.demo.domain.event.dto.response.EventDto;
import com.example.demo.domain.event.service.EventService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor 
@RequestMapping("/v1/event")
public class EventController {
    
    private final EventService eventService;

    @PostMapping("/add")
    public ResponseDto addEvent(@RequestBody RequestAddEventDto dto){
        eventService.addEvent(dto);

        return new ResponseDto();
    }

    @GetMapping("/search")
    public ResponseDto searchEvent(@RequestParam (value = "q") String q){
        eventService.searchEvent(q);

        return new ResponseDto();
    }

    @GetMapping("/{id}")
    public ResponseDto getEvent(@RequestParam (value="id") Long id){
        EventDto eventDto = eventService.getEvent(id);
        return new ResponseDto(eventDto);
    }

    @PostMapping("/regist")
    public ResponseDto registrationEvent(@RequestBody RequestRegistrationEventDto dto){
        return new ResponseDto();
    }
}
