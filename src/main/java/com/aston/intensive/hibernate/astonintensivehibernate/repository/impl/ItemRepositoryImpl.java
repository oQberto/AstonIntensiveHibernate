package com.aston.intensive.hibernate.astonintensivehibernate.repository.impl;

import com.aston.intensive.hibernate.astonintensivehibernate.model.entity.Item;
import com.aston.intensive.hibernate.astonintensivehibernate.repository.ItemRepository;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
у тебя тут нет обработок исключений проверок на null
    можно чтонибудь про работу транзакций подумать
@Repository
public class ItemRepositoryImpl implements ItemRepository {
методы открывают сессии Hibernate, но не закрывают их
    @Override
    public Optional<Item> findById(Session session, Long id) {
        return Optional.ofNullable(
                session.get(Item.class, id)
        );
    }

    @Override
    public Optional<Item> findByName(Session session, String name) {
        return Optional.ofNullable(session
                .createQuery("""
                        select i
                        from Item i
                        where i.name = :name
                        """, Item.class)
                .setParameter("name", name)
                .uniqueResult()
        );
    }

    @Override
    public List<Item> findAll(Session session, int page, int size) {
        return session
                .createQuery("from Item", Item.class)
                .setReadOnly(true)
                .setFirstResult(page)
                .setMaxResults(size)
                .list();
    }

    @Override
    public Item save(Session session, Item item) {
        session.persist(item);

        return item;
    }

    @Override
    public Item update(Session session, Item item) {
        return session.merge(item);
    }

    @Override
    public Long delete(Session session, Long id) {
        var item = session.get(Item.class, id);

        session.remove(item);

        return id;
    }
}
