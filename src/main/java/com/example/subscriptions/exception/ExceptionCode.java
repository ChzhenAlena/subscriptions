package com.example.subscriptions.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionCode {
  INTERNAL_ERROR("Internal server error"),
  VALIDATION_ERROR("Validation failed"),
  INVALID_REQUEST("Invalid request format"),

  USER_NOT_FOUND("User not found"),
  USER_ALREADY_EXISTS("User with such email already exists"),
  SUBSCRIPTION_NOT_FOUND("Subscription not found"),
  SUBSCRIPTION_ALREADY_EXISTS("Subscription with such name already exists");

  private final String message;
}
