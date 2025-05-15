package com.example.subscriptions.service;

import com.example.subscriptions.dto.subscription.SubscriptionDto;
import com.example.subscriptions.entity.Subscription;
import com.example.subscriptions.entity.User;
import com.example.subscriptions.exception.ExceptionCode;
import com.example.subscriptions.exception.ServiceException;
import com.example.subscriptions.mapper.SubscriptionMapper;
import com.example.subscriptions.repository.SubscriptionRepository;
import com.example.subscriptions.repository.UserRepository;
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
public class UserSubscriptionService {
  private final UserRepository userRepository;
  private final SubscriptionRepository subscriptionRepository;
  private final SubscriptionMapper subscriptionMapper;

  public List<SubscriptionDto> addSubscription(Long userId, Long subscriptionId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() ->  new ServiceException(ExceptionCode.USER_NOT_FOUND, HttpStatus.NOT_FOUND));

    Subscription subscription = subscriptionRepository.findById(subscriptionId)
        .orElseThrow(() ->  new ServiceException(ExceptionCode.SUBSCRIPTION_NOT_FOUND, HttpStatus.NOT_FOUND));

    user.addSubscription(subscription);
    log.debug("User with id={} added subscription with id={}", userId, subscriptionId);

    return user.getSubscriptions().stream()
        .map(subscriptionMapper::toDto)
        .toList();
  }

  @Transactional(readOnly = true)
  public List<SubscriptionDto> getUserSubscriptions(Long userId) {
    if (!userRepository.existsById(userId)) {
      throw new ServiceException(ExceptionCode.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
    }
    return subscriptionRepository.findByUserId(userId).stream()
        .map(subscriptionMapper::toDto)
        .toList();
  }

  public void deleteSubscription(Long userId, Long subscriptionId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ServiceException(ExceptionCode.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
    Subscription subscription = subscriptionRepository.findById(subscriptionId)
        .orElseThrow(() -> new ServiceException(ExceptionCode.SUBSCRIPTION_NOT_FOUND, HttpStatus.NOT_FOUND));
    user.removeSubscription(subscription);
    log.debug("User with id={} deleted subscription with id={}", userId, subscriptionId);
  }
}