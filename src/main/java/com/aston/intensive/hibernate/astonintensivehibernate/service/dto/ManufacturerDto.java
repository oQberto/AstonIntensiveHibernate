package com.aston.intensive.hibernate.astonintensivehibernate.service.dto;

import com.aston.intensive.hibernate.astonintensivehibernate.model.enums.ManufacturerName;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ManufacturerDto {
    Long id;
    ManufacturerName manufacturerName;
}
