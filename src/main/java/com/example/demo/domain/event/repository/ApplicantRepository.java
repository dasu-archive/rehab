package com.example.demo.domain.event.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.event.entity.Applicant;
import com.example.demo.domain.event.entity.Event;
import com.example.demo.domain.member.entity.Member;

public interface ApplicantRepository extends JpaRepository<Applicant,Long>{

    Optional<Applicant> findByMemberAndEvent(Member member, Event event);
    
}
