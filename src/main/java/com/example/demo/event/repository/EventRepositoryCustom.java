package com.example.demo.event.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.event.entity.Event;

public interface EventRepositoryCustom {
    Page<Event> search(String q, Boolean reamin, Pageable pageable);
    
}
