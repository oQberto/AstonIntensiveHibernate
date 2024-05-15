package com.aston.intensive.hibernate.astonintensivehibernate.service.dto;

import com.aston.intensive.hibernate.astonintensivehibernate.model.enums.CountryCode;
import com.aston.intensive.hibernate.astonintensivehibernate.model.enums.CountryName;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CountryOfOriginDto {
    Long id;
    CountryCode countryCode;
    CountryName countryName;
}
