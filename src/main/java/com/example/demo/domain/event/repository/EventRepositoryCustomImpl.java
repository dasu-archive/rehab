package com.example.demo.domain.event.repository;

import static com.example.demo.domain.event.entity.QEvent.event;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import com.example.demo.config.exception.CustomException;
import com.example.demo.config.exception.ErrorCode;
import com.example.demo.domain.event.entity.Event;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 
public class EventRepositoryCustomImpl implements EventRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Event> search(String q, Boolean remain, Pageable pageable) {
        
        BooleanExpression nameQuery = q != null && StringUtils.hasText(q) ? event.name.contains(q) : null;
        BooleanExpression remainQuery = remain == null ? null : event.capacity.gt(event.applicantCount);
        
        List<OrderSpecifier<?>> orders = new ArrayList<>();

        for (Sort.Order o : pageable.getSort()) {
            Order direction = o.isAscending() ? Order.ASC : Order.DESC;
            switch (o.getProperty()) {
                case "name"     -> orders.add(new OrderSpecifier<>(direction, event.name));
                case "capacity" -> orders.add(new OrderSpecifier<>(direction, event.capacity));
                case "id"       -> orders.add(new OrderSpecifier<>(direction, event.id));
                default -> throw new CustomException(ErrorCode.INVALID_SORT);   // 또는 무시
            }
        }

        orders.add(event.id.desc()); 

        List<Event> eventList = queryFactory
                .selectFrom(event)
                .where(nameQuery,remainQuery)
                .orderBy(orders.toArray(OrderSpecifier[]::new))
                .fetch();

        
        Long count = queryFactory
                .select(event.count())
                .from(event)
                .where(nameQuery,remainQuery)
                .fetchOne();
                
        count = count == null ? 0L : count;        
    
        return new PageImpl<>(eventList, pageable, count);
    }
    
}
