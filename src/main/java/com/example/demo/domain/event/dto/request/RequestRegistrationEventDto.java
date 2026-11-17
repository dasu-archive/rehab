package com.example.demo.domain.event.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter 
public class RequestRegistrationEventDto {
    
    @NotBlank 
    Long eventId;

    @NotBlank 
    String username;



}
