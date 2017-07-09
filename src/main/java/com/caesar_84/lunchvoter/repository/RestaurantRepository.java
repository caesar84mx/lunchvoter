package com.caesar_84.lunchvoter.repository;

import com.caesar_84.lunchvoter.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
