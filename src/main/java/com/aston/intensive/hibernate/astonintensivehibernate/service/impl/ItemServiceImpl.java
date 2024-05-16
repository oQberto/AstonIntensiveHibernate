package com.aston.intensive.hibernate.astonintensivehibernate.service.impl;

import com.aston.intensive.hibernate.astonintensivehibernate.repository.ItemRepository;
import com.aston.intensive.hibernate.astonintensivehibernate.service.ItemService;
import com.aston.intensive.hibernate.astonintensivehibernate.service.dto.item.ItemCreateEditDto;
import com.aston.intensive.hibernate.astonintensivehibernate.service.dto.item.ItemRepresentationDto;
import com.aston.intensive.hibernate.astonintensivehibernate.service.exception.EntityCreationException;
import com.aston.intensive.hibernate.astonintensivehibernate.service.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static lombok.AccessLevel.PRIVATE;
// может буду не прав, но я бы работу с сессиями вынес бы в репозиторий, а в сервисе оставил доп логику или с маппингом и проверкой на ошибки. 
Тут тож важный момент, в работе с сессиями нужно продумать случаи когда нужно откатить транзакцию 
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class ItemServiceImpl implements ItemService {
    SessionFactory sessionFactory;
    ItemRepository itemRepository;
    ItemMapper itemMapper;

    @Override
    public Optional<ItemRepresentationDto> findById(Long id) {
        Optional<ItemRepresentationDto> dto;

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            dto = itemRepository.findById(session, id)
                    .map(itemMapper::mapToDto);

            session.getTransaction().commit();
        }

        return dto;
    }

    @Override
    public List<ItemRepresentationDto> findAll(int page, int size) {
        List<ItemRepresentationDto> items;

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            items = itemRepository.findAll(session, page, size)
                    .stream()
                    .map(itemMapper::mapToDto)
                    .toList();

            session.getTransaction().commit();
        }

        return items;
    }
В методы save и update можно добавить валидацию
    @Override
    public ItemRepresentationDto save(ItemCreateEditDto dto) {
        ItemRepresentationDto savedItem;

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            savedItem = ofNullable(dto)
                    .map(itemMapper::mapToEntity)
                    .map(item -> itemRepository.save(session, item))
                    .map(itemMapper::mapToDto)
                    .orElseThrow(() -> new EntityCreationException("Couldn't create an entity"));

            session.getTransaction().commit();
        }

        return savedItem;
    }

    @Override
    public Optional<ItemRepresentationDto> update(ItemCreateEditDto dto, String name) {
        Optional<ItemRepresentationDto> updatedItem;

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            updatedItem = itemRepository.findByName(session, name)
                    .map(item -> itemMapper.updateItem(dto, item))
                    .map(item -> itemRepository.update(session, item))
                    .map(itemMapper::mapToDto);

            session.getTransaction().commit();
        }

        return updatedItem;
    }

    @Override
    public Long delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            itemRepository.delete(session, id);

            session.getTransaction().commit();
        }

        return id;
    }
}
