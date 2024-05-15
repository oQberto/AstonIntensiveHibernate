package com.aston.intensive.hibernate.astonintensivehibernate.service.mapper;

import com.aston.intensive.hibernate.astonintensivehibernate.model.entity.Item;
import com.aston.intensive.hibernate.astonintensivehibernate.service.dto.item.ItemCreateEditDto;
import com.aston.intensive.hibernate.astonintensivehibernate.service.dto.item.ItemRepresentationDto;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Component
@Mapper(componentModel = SPRING,
        uses = {
                ManufacturerMapper.class,
                CountryOfOriginMapper.class
        })
public interface ItemMapper {

    @Mappings({
            @Mapping(target = "manufacturerDto", source = "manufacturer"),
            @Mapping(target = "countryOfOriginDto", source = "countryOfOrigin")
    })
    ItemRepresentationDto mapToDto(Item item);

    @Mappings({
            @Mapping(target = "manufacturer", source = "manufacturerDto"),
            @Mapping(target = "countryOfOrigin", source = "countryOfOriginDto")
    })
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    Item mapToEntity(ItemCreateEditDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    Item updateItem(ItemCreateEditDto dto, @MappingTarget Item entity);
}
