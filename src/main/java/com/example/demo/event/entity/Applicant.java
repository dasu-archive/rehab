package com.example.demo.event.entity;

import com.example.demo.member.entity.Member;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="applicant")
@Getter 
@NoArgsConstructor 
public class Applicant {
    

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long id;
    
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "event_id")
    Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "member_id")
    Member member;


    public Applicant(Member member, Event event){
        this.member = member;
        this.event = event;
    }
}
