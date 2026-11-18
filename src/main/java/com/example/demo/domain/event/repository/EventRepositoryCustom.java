package com.example.demo.domain.event.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.demo.domain.event.entity.Event;

public interface EventRepositoryCustom {
    Page<Event> search(String q, Boolean reamin, Pageable pageable);
    
}
