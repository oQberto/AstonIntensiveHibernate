package com.aston.intensive.hibernate.astonintensivehibernate.service.impl;

import com.aston.intensive.hibernate.astonintensivehibernate.repository.OrderRepository;
import com.aston.intensive.hibernate.astonintensivehibernate.service.OrderService;
import com.aston.intensive.hibernate.astonintensivehibernate.service.dto.order.OrderDto;
import com.aston.intensive.hibernate.astonintensivehibernate.service.exception.EntityCreationException;
import com.aston.intensive.hibernate.astonintensivehibernate.service.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class OrderServiceImpl implements OrderService {
    SessionFactory sessionFactory;
    OrderRepository orderRepository;
    OrderMapper orderMapper;

    @Override
    public Optional<OrderDto> findById(Long id) {
        Optional<OrderDto> dto;

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            dto = orderRepository.findById(session, id)
                    .map(orderMapper::mapToDto);

            session.getTransaction().commit();
        }

        return dto;
    }

    @Override
    public List<OrderDto> findAll(int page, int pageSize) {
        List<OrderDto> orders;

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            orders = orderRepository.findAll(session, page, pageSize)
                    .stream()
                    .map(orderMapper::mapToDto)
                    .toList();

            session.getTransaction().commit();
        }

        return orders;
    }

    @Override
    public OrderDto save(OrderDto orderDto) {
        OrderDto savedOrder;

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            savedOrder = ofNullable(orderDto)
                    .map(orderMapper::mapToEntity)
                    .map(order -> orderRepository.save(session, order))
                    .map(orderMapper::mapToDto)
                    .orElseThrow(() -> new EntityCreationException("Couldn't create an entity"));

            session.getTransaction().commit();
        }

        return savedOrder;
    }

    @Override
    public Optional<OrderDto> update(OrderDto orderDto, Long id) {
        Optional<OrderDto> updatedOrder;

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            updatedOrder = orderRepository.findById(session, id)
                    .map(order -> orderMapper.updateOrder(orderDto, order))
                    .map(order -> orderRepository.update(session, order))
                    .map(orderMapper::mapToDto);

            session.getTransaction().commit();
        }

        return updatedOrder;
    }

    @Override
    public Long delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            orderRepository.delete(session, id);

            session.getTransaction().commit();
        }

        return id;
    }
}
