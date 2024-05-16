package com.aston.intensive.hibernate.astonintensivehibernate.service.dto.order;

import com.aston.intensive.hibernate.astonintensivehibernate.model.enums.Status;
import com.aston.intensive.hibernate.astonintensivehibernate.service.dto.item.ItemRepresentationDto;
import com.aston.intensive.hibernate.astonintensivehibernate.service.dto.user.UserRepresentationDto;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder
public class OrderDto {
    Long id;
    Status status;
    LocalDateTime time;
    UserRepresentationDto userDto;
    List<ItemRepresentationDto> dtoItems;
}
