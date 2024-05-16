package com.aston.intensive.hibernate.astonintensivehibernate.repository.impl;

import com.aston.intensive.hibernate.astonintensivehibernate.model.entity.Order;
import com.aston.intensive.hibernate.astonintensivehibernate.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@Repository
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class OrderRepositoryImpl implements OrderRepository {

    @Override
    public Optional<Order> findById(Session session, Long id) {
        return Optional.ofNullable(
                session.get(Order.class, id)
        );
    }

    @Override
    public List<Order> findAll(Session session, int page, int size) {
        return session
                .createQuery("select o from Order o", Order.class)
                .setFirstResult(page)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public Order save(Session session, Order entity) {
        session.persist(entity);

        return entity;
    }

    @Override
    public Order update(Session session, Order entity) {
        return session.merge(entity);
    }

    @Override
    public Long delete(Session session, Long id) {
        var deletableOrder = session.get(Order.class, id);

        session.remove(deletableOrder);

        return id;
    }
}
