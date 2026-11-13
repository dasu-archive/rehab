package com.example.demo.domain.event.entity;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.domain.event.dto.response.EventDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "event")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "capacity")
    Integer capacity;

    @Column(name = "applicant_count")
    Integer applicantCount;

    @OneToMany(mappedBy="event")
    List<Applicant> applicants = new ArrayList<>();

    public Event(String name, Integer capacity) {
        this.name = name;
        this.capacity = capacity;
        this.applicantCount = 0;
    }

    public EventDto toEventDto() {
        return new EventDto(this.name, this.id);
    }

}
