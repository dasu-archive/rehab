package com.example.demo.event.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.event.entity.Applicant;
import com.example.demo.event.entity.Event;
import com.example.demo.member.entity.Member;

public interface ApplicantRepository extends JpaRepository<Applicant,Long>{

    Optional<Applicant> findByMemberAndEvent(Member member, Event event);
    
}
