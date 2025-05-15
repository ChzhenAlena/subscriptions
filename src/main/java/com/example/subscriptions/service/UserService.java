package com.example.subscriptions.service;

import com.example.subscriptions.dto.user.UserCreateDto;
import com.example.subscriptions.dto.user.UserDto;
import com.example.subscriptions.dto.user.UserShortDto;
import com.example.subscriptions.dto.user.UserUpdateDto;
import com.example.subscriptions.entity.User;
import com.example.subscriptions.exception.ExceptionCode;
import com.example.subscriptions.exception.ServiceException;
import com.example.subscriptions.mapper.UserMapper;
import com.example.subscriptions.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserMapper userMapper;

  public UserDto create(UserCreateDto createDto) {
    if (userRepository.existsByEmail(createDto.getEmail())) {
      throw new ServiceException(ExceptionCode.USER_ALREADY_EXISTS, HttpStatus.CONFLICT);
    }
    User user = userMapper.toEntity(createDto);
    user.setPassword(passwordEncoder.encode(createDto.getPassword()));
    user = userRepository.save(user);
    log.debug("User with id={} created", user.getId());
    return userMapper.toDto(user);
  }

  @Transactional(readOnly = true)
  public List<UserShortDto> findAll() {
    return userRepository.findAll().stream()
        .map(userMapper::toShortDto)
        .toList();
  }

  @Transactional(readOnly = true)
  public UserDto getById(Long id) {
    return userRepository.findById(id)
        .map(userMapper::toDto)
        .orElseThrow(() -> new ServiceException(ExceptionCode.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
  }

  public UserDto update(Long id, UserUpdateDto dto) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new ServiceException(ExceptionCode.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
    userMapper.updateUserFromDto(dto, user);
    user = userRepository.save(user);
    return userMapper.toDto(user);
  }

  public void delete(Long id) {
    if (!userRepository.existsById(id)) {
      throw new ServiceException(ExceptionCode.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
    }
    userRepository.deleteById(id);
    log.debug("User with id={} deleted", id);
  }
}