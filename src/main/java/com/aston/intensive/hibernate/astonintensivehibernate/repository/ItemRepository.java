package com.aston.intensive.hibernate.astonintensivehibernate.repository;

import com.aston.intensive.hibernate.astonintensivehibernate.model.entity.Item;
import org.hibernate.Session;

import java.util.Optional;

public interface ItemRepository extends CommonCrudRepository<Item, Long> {

    Optional<Item> findByName(Session session, String name);
}
