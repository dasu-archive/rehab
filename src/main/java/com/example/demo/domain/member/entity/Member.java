package com.example.demo.domain.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="member")
@Getter 
@NoArgsConstructor (access=AccessLevel.PROTECTED)
public class Member {
    
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;
    

    @Column (name="name")
    String name;

    @Column (name="email")
    String email;
}
