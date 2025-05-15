package com.example.subscriptions.service;

import com.example.subscriptions.dto.subscription.SubscriptionCreateDto;
import com.example.subscriptions.dto.subscription.SubscriptionDto;
import com.example.subscriptions.dto.subscription.SubscriptionUpdateDto;
import com.example.subscriptions.entity.Subscription;
import com.example.subscriptions.exception.ExceptionCode;
import com.example.subscriptions.exception.ServiceException;
import com.example.subscriptions.mapper.SubscriptionMapper;
import com.example.subscriptions.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class SubscriptionService {
  private final SubscriptionRepository subscriptionRepository;
  private final SubscriptionMapper subscriptionMapper;

  public SubscriptionDto create(SubscriptionCreateDto createDto) {
    if (subscriptionRepository.existsByName(createDto.getName())) {
      throw new ServiceException(ExceptionCode.SUBSCRIPTION_ALREADY_EXISTS, HttpStatus.CONFLICT);
    }
    Subscription subscription = subscriptionMapper.toEntity(createDto);
    subscription = subscriptionRepository.save(subscription);
    log.debug("Subscription with id={} created", subscription.getId());
    return subscriptionMapper.toDto(subscription);
  }

  @Transactional(readOnly = true)
  public List<SubscriptionDto> findAll() {
    return subscriptionRepository.findAll().stream()
        .map(subscriptionMapper::toDto)
        .toList();
  }

  @Transactional(readOnly = true)
  public SubscriptionDto getById(Long id) {
    return subscriptionRepository.findById(id)
        .map(subscriptionMapper::toDto)
        .orElseThrow(() -> new ServiceException(ExceptionCode.SUBSCRIPTION_NOT_FOUND, HttpStatus.NOT_FOUND));
  }

  public SubscriptionDto update(Long id, SubscriptionUpdateDto dto) {
    Subscription subscription = subscriptionRepository.findById(id)
        .orElseThrow(() -> new ServiceException(ExceptionCode.SUBSCRIPTION_NOT_FOUND, HttpStatus.NOT_FOUND));
    subscriptionMapper.updateUserFromDto(dto, subscription);
    subscription = subscriptionRepository.save(subscription);
    return subscriptionMapper.toDto(subscription);
  }

  public void delete(Long id) {
    if (!subscriptionRepository.existsById(id)) {
      throw new ServiceException(ExceptionCode.SUBSCRIPTION_NOT_FOUND, HttpStatus.NOT_FOUND);
    }
    subscriptionRepository.deleteById(id);
    log.debug("Subscription with id={} deleted", id);
  }
}