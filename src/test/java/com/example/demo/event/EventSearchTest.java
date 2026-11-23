package com.example.demo.event;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.domain.event.entity.Event;
import com.example.demo.domain.event.repository.EventRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest 
@Slf4j 
@ActiveProfiles ("test")
public class EventSearchTest {
    
    @Autowired EventRepository eventRepository;

    @BeforeEach
    void 행사_3개() {
        
    }

    @Test
    void 조건_없음은_페이지_크기만큼만() {
        Page<Event> page = eventRepository.search(null, null, PageRequest.of(0, 2));

        assertThat(page.getContent()).hasSize(2);   // 지금 코드는 3 → 1번 버그
        assertThat(page.getTotalElements()).isEqualTo(3);
    }

    
}
