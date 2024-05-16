package com.aston.intensive.hibernate.astonintensivehibernate.service.mapper;

import com.aston.intensive.hibernate.astonintensivehibernate.model.entity.Order;
import com.aston.intensive.hibernate.astonintensivehibernate.service.dto.order.OrderDto;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Component
@Mapper(componentModel = SPRING,
        uses = {
                ItemMapper.class,
                UserMapper.class
        })
public interface OrderMapper {

    @Mappings({
            @Mapping(target = "userDto", source = "user"),
            @Mapping(target = "dtoItems", source = "items")
    })
    OrderDto mapToDto(Order order);

    @Mappings({
            @Mapping(target = "user", source = "userDto"),
            @Mapping(target = "items", source = "dtoItems")
    })
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    Order mapToEntity(OrderDto orderDto);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    Order updateOrder(OrderDto orderDto, @MappingTarget Order order);
}
