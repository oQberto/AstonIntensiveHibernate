package com.aston.intensive.hibernate.astonintensivehibernate.service.dto.item;

import com.aston.intensive.hibernate.astonintensivehibernate.model.enums.Type;
import com.aston.intensive.hibernate.astonintensivehibernate.service.dto.CountryOfOriginDto;
import com.aston.intensive.hibernate.astonintensivehibernate.service.dto.ManufacturerDto;
import com.aston.intensive.hibernate.astonintensivehibernate.validation.CreateAction;
import com.aston.intensive.hibernate.astonintensivehibernate.validation.UpdateAction;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class ItemCreateEditDto {

    @NotNull(groups = CreateAction.class)
    @Size(min = 1, max = 128, groups = {CreateAction.class, UpdateAction.class})
    String name;

    @NotNull(groups = CreateAction.class)
    Type type;

    @NotNull(groups = {CreateAction.class})
    @DecimalMin(value = "0.0", groups = {CreateAction.class, UpdateAction.class})
    BigDecimal price;

    @NotNull(groups = CreateAction.class)
    @Min(value = 0, groups = {CreateAction.class, UpdateAction.class})
    Integer quantity;

    @NotNull(groups = CreateAction.class)
    @DecimalMax(value = "1.0", groups = {CreateAction.class, UpdateAction.class})
    @DecimalMin(value = "0.0", groups = {CreateAction.class, UpdateAction.class})
    Double discount;

    @NotNull(groups = CreateAction.class)
    ManufacturerDto manufacturerDto;

    @NotNull(groups = CreateAction.class)
    CountryOfOriginDto countryOfOriginDto;
}
