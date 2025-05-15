package com.example.subscriptions.controller;

import com.example.subscriptions.dto.user.UserCreateDto;
import com.example.subscriptions.dto.user.UserDto;
import com.example.subscriptions.dto.user.UserShortDto;
import com.example.subscriptions.dto.user.UserUpdateDto;
import com.example.subscriptions.service.UserService;
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
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserDto create(@RequestBody @Valid UserCreateDto dto) {
    return userService.create(dto);
  }

  @GetMapping
  public List<UserShortDto> findAll() {
    return userService.findAll();
  }

  @GetMapping("/{id}")
  public UserDto getById(@PathVariable Long id) {
    return userService.getById(id);
  }

  @PutMapping("/{id}")
  public UserDto update(
      @PathVariable Long id,
      @RequestBody @Valid UserUpdateDto dto
  ) {
    return userService.update(id, dto);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    userService.delete(id);
  }
}