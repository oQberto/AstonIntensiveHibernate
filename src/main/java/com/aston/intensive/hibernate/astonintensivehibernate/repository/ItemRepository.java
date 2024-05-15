package com.aston.intensive.hibernate.astonintensivehibernate.repository;

import com.aston.intensive.hibernate.astonintensivehibernate.model.entity.Item;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {

    Optional<Item> findById(Session session, int id);

    Optional<Item> findByName(Session session, String name);

    List<Item> findAll(Session session, int page, int size);

    Item save(Session session, Item item);

    Item update(Session session, Item item);

    Long delete(Session session, Long id);
}
