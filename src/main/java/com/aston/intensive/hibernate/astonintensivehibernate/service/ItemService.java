package com.aston.intensive.hibernate.astonintensivehibernate.service;

import com.aston.intensive.hibernate.astonintensivehibernate.service.dto.item.ItemCreateEditDto;
import com.aston.intensive.hibernate.astonintensivehibernate.service.dto.item.ItemRepresentationDto;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    Optional<ItemRepresentationDto> findById(Long id);

    List<ItemRepresentationDto> findAll(int page, int size);

    ItemRepresentationDto save(ItemCreateEditDto item);

    Optional<ItemRepresentationDto> update(ItemCreateEditDto item, String name);

    Long delete(Long id);
}
