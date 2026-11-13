package com.example.demo.domain.event.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/v1/events")
public class EventController {
    
    private final EventService eventService;

    @PostMapping()
    public ResponseEntity<ResponseDto<Void>> addEvent(@RequestBody RequestAddEventDto dto){
        eventService.addEvent(dto);

        return ResponseDto.ok(null);
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseDto<Void>> searchEvent(@RequestParam (value = "q") String q){
        eventService.searchEvent(q);

        return ResponseDto.ok(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<EventDto>> getEvent(@PathVariable  (value="id") Long id){
        EventDto eventDto = eventService.getEvent(id);
        return ResponseDto.ok(eventDto);
    }

    @PostMapping("/regist")
    public ResponseEntity<ResponseDto<Void>> registrationEvent(@RequestBody RequestRegistrationEventDto dto){
        return ResponseDto.ok(null);
    }
}
