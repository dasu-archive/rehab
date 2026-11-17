package com.example.demo.domain.event.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.event.entity.Event;

/**
 * EventRepository
 */
public interface EventRepository extends JpaRepository<Event,Long>{


    Page<Event> findByNameContaining(String q, Pageable pageable);


}
