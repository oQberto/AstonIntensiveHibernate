package com.aston.intensive.hibernate.astonintensivehibernate.service.mapper;

import com.aston.intensive.hibernate.astonintensivehibernate.model.entity.CountryOfOrigin;
import com.aston.intensive.hibernate.astonintensivehibernate.service.dto.CountryOfOriginDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Component
@Mapper(componentModel = SPRING)
public interface CountryOfOriginMapper {

    CountryOfOriginDto mapToDto(CountryOfOrigin countryOfOrigin);

    @Mapping(target = "items", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    CountryOfOrigin mapToEntity(CountryOfOriginDto countryOfOriginDto);
}
