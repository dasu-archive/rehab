package com.example.demo.domain.event.dto.request;


import jakarta.validation.constraints.NotBlank;
 
public record RequestRegistrationEventDto(
    
    @NotBlank Long eventId,
    @NotBlank String username
){}

