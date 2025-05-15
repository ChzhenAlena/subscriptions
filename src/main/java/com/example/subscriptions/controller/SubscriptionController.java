package com.example.subscriptions.controller;

import com.example.subscriptions.dto.subscription.SubscriptionCreateDto;
import com.example.subscriptions.dto.subscription.SubscriptionDto;
import com.example.subscriptions.dto.subscription.SubscriptionUpdateDto;
import com.example.subscriptions.service.SubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
  private final SubscriptionService subscriptionService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public SubscriptionDto create(@RequestBody @Valid SubscriptionCreateDto dto) {
    return subscriptionService.create(dto);
  }

  @GetMapping
  public List<SubscriptionDto> findAll() {
    return subscriptionService.findAll();
  }

  @GetMapping("/{id}")
  public SubscriptionDto getById(@PathVariable Long id) {
    return subscriptionService.getById(id);
  }

  @PutMapping("/{id}")
  public SubscriptionDto update(
      @PathVariable Long id,
      @RequestBody @Valid SubscriptionUpdateDto dto
  ) {
    return subscriptionService.update(id, dto);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    subscriptionService.delete(id);
  }
}