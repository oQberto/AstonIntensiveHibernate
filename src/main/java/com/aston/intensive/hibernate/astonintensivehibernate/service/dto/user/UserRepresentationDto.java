package com.aston.intensive.hibernate.astonintensivehibernate.service.dto.user;

import com.aston.intensive.hibernate.astonintensivehibernate.model.enums.Role;
import com.aston.intensive.hibernate.astonintensivehibernate.service.dto.order.OrderDto;
import lombok.Builder;
import lombok.Value;

import java.util.HashSet;
import java.util.Set;

@Value
@Builder
public class UserRepresentationDto {
    Long id;
    String userName;
    String email;
    Role role;
    Set<OrderDto> orders = new HashSet<>();
}
