package com.aston.intensive.hibernate.astonintensivehibernate.repository;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommonCrudRepository<ENTITY, ID> {

    Optional<ENTITY> findById(Session session, ID id);

    List<ENTITY> findAll(Session session, int page, int size);

    ENTITY save(Session session, ENTITY entity);

    ENTITY update(Session session, ENTITY entity);

    ID delete(Session session, ID id);
}
