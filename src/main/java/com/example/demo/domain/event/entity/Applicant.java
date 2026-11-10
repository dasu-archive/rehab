package com.example.demo.domain.event.entity;

import org.aspectj.lang.annotation.RequiredTypes;

import com.example.demo.domain.member.entity.Member;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity(name="applicant")
@Data 
public class Applicant {
    

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long id;
    
    
    @ManyToOne 
    @JoinColumn (name = "event_id")
    Event event;

    @ManyToOne
    @JoinColumn (name = "member_id")
    Member member;


    public Applicant(Member member, Event event){
        this.member = member;
        this.event = event;
    }
}
