package com.example.demo.domain.event.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record RequestAddEventDto(    
    @NotBlank String eventName,
    @Min(1) Integer capacity
) {
} 