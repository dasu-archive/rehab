package com.example.demo.domain.event.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.event.entity.Event;

/**
 * EventRepository
 */
@Repository 
public interface EventRepository extends JpaRepository<Event,Long>{


    Page<Event> findByNameContaining(String q, Pageable pageable);

    @Query("SELECT e FROM Event e left join fetch e.host ")
    List<Event> findAllFetchJoin();

    @EntityGraph (attributePaths = "host")
    List<Event> findAllWithEntityGraphBy();
}
