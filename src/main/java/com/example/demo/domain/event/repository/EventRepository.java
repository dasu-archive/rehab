package com.example.demo.domain.event.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.event.entity.Event;

/**
 * EventRepository
 */
public interface EventRepository extends JpaRepository<Event,Long>{


}
