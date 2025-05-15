package com.example.subscriptions.controller;

import com.example.subscriptions.dto.subscription.SubscriptionDto;
import com.example.subscriptions.mapper.SubscriptionMapper;
import com.example.subscriptions.service.UserSubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserSubscriptionController {
  private final UserSubscriptionService userSubscriptionService;
  private final SubscriptionMapper subscriptionMapper;

  @PostMapping("/{id}/subscriptions/{sub_id}")
  @ResponseStatus(HttpStatus.CREATED)
  public List<SubscriptionDto> addSubscription(
      @RequestParam("id") Long userId,
      @PathVariable("sub_id") Long subscriptionId
  ) {
    return userSubscriptionService.addSubscription(userId, subscriptionId);
  }

  @GetMapping("/{id}/subscriptions")
  public List<SubscriptionDto> getUserSubscriptions(
      @RequestParam("id") Long userId
  ) {
    return userSubscriptionService.getUserSubscriptions(userId);
  }

  @DeleteMapping("/{id}/subscriptions/{sub_id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteSubscription(
      @PathVariable("id") Long userId,
      @PathVariable("sub_id") Long subscriptionId
  ) {
    userSubscriptionService.deleteSubscription(userId, subscriptionId);
  }
}