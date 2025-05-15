package com.example.subscriptions.mapper;

import com.example.subscriptions.dto.user.UserCreateDto;
import com.example.subscriptions.dto.user.UserDto;
import com.example.subscriptions.dto.user.UserShortDto;
import com.example.subscriptions.dto.user.UserUpdateDto;
import com.example.subscriptions.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

  @Mapping(target = "subscriptions", defaultExpression = "java(new java.util.ArrayList<>())")
  UserDto toDto(User user);
  UserShortDto toShortDto(User user);
  User toEntity(UserCreateDto dto);
  void updateUserFromDto(UserUpdateDto dto, @MappingTarget User user);

}

