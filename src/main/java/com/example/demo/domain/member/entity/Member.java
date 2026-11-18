package com.example.demo.domain.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table (name="member")
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
    

    public Member(String name){
        this.name = name;
        this.email = name + "@"+ name + ".com";
    }
}
