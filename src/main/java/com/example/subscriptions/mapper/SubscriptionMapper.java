package com.example.subscriptions.mapper;

import com.example.subscriptions.dto.subscription.SubscriptionCreateDto;
import com.example.subscriptions.dto.subscription.SubscriptionDto;
import com.example.subscriptions.dto.subscription.SubscriptionUpdateDto;
import com.example.subscriptions.entity.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubscriptionMapper {

  Subscription toEntity(SubscriptionCreateDto dto);
  SubscriptionDto toDto(Subscription subscription);
  void updateUserFromDto(SubscriptionUpdateDto dto, @MappingTarget Subscription subscription);

}

