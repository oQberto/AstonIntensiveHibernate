package com.aston.intensive.hibernate.astonintensivehibernate.service.dto.item;

import com.aston.intensive.hibernate.astonintensivehibernate.model.enums.Type;
import com.aston.intensive.hibernate.astonintensivehibernate.service.dto.CountryOfOriginDto;
import com.aston.intensive.hibernate.astonintensivehibernate.service.dto.ManufacturerDto;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Builder
@Value
public class ItemRepresentationDto {
        Long id;
        String name;
        Type type;
        BigDecimal price;
        Integer quantity;
        Double discount;
        ManufacturerDto manufacturerDto;
        CountryOfOriginDto countryOfOriginDto;
}
