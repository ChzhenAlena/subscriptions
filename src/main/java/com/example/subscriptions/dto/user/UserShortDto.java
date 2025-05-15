package com.example.subscriptions.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserShortDto {
  private Long id;
  private String username;
  private String email;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}