package com.aston.intensive.hibernate.astonintensivehibernate.service.mapper;

import com.aston.intensive.hibernate.astonintensivehibernate.model.entity.User;
import com.aston.intensive.hibernate.astonintensivehibernate.service.dto.user.UserRepresentationDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Component
@Mapper(componentModel = SPRING)
public interface UserMapper {

    UserRepresentationDto mapToDto(User user);
}
