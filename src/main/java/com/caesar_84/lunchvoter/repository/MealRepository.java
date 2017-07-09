package com.caesar_84.lunchvoter.repository;

import com.caesar_84.lunchvoter.domain.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional
public interface MealRepository extends JpaRepository<Meal, Integer> {
    @Query("SELECT m FROM Meal m WHERE m.menu.id=:menu_id")
    List<Meal> getByLunch(@Param("menu_id") int menuId);
}