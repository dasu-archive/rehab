package com.example.demo.domain.event.entity;

import java.util.List;

import org.springframework.data.repository.cdi.Eager;

import com.example.demo.domain.event.dto.request.RequestAddEventDto;
import com.example.demo.domain.event.dto.response.EventDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity 
@Data 
public class Event {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long id;


    @Column(name="name")
    String name;

    @Column (name = "capacity")
    Integer capacity;

    @Column (name = "applicant_count")
    Integer applicantCount;
 
    @OneToMany
    List<Applicant> applicants;

    public Event(String name, Integer capacity){
        this.name = name;
        this.capacity = capacity;
    }

    public EventDto toEventDto() {
        return new EventDto(this.name, this.id);        
    }

    
}
