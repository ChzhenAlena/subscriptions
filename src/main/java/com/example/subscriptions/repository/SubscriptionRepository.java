package com.example.subscriptions.repository;

import com.example.subscriptions.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
  @Query("SELECT s FROM Subscription s JOIN s.users u WHERE u.id = :userId")
  List<Subscription> findByUserId(@Param("userId") Long userId);

  boolean existsByName(String name);
}
