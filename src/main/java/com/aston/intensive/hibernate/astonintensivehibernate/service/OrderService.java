package com.aston.intensive.hibernate.astonintensivehibernate.service;


import com.aston.intensive.hibernate.astonintensivehibernate.service.dto.order.OrderDto;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Optional<OrderDto> findById(Long id);

    List<OrderDto> findAll(int page, int pageSize);

    OrderDto save(OrderDto orderDto);

    Optional<OrderDto> update(OrderDto orderDto, Long id);

    Long delete(Long id);
}
