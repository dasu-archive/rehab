package com.example.demo.event.entity;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.event.dto.response.EventDto;
import com.example.demo.member.entity.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "event")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name="host_id")
    Member host;

    @OneToMany(mappedBy="event")
    List<Applicant> applicants = new ArrayList<>();

    public Event(String name, Integer capacity) {
        this.name = name;
        this.capacity = capacity;
        this.applicantCount = 0;
        this.host=null;
    }

    public Event(String name, Integer capacity, Member host){
        this.name= name;
        this.capacity = capacity;
        this.applicantCount = 0;
        this.host = host;
    }

    public EventDto toEventDto() {
        return new EventDto(this.name, this.id);
    }

    public void addApplicantCount() {
        this.applicantCount++;
    }

}
