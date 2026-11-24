package com.example.demo.global.database;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter 
@MappedSuperclass 
public class CreatedAtEntity {

    @CreatedDate 
    @Column(nullable=false, updatable=false)
    private LocalDateTime createdAt;

    
    
}
