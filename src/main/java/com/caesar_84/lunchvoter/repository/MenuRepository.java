package com.caesar_84.lunchvoter.repository;

import com.caesar_84.lunchvoter.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RepositoryRestResource
@Transactional
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    @Transactional(readOnly = true)
    @Query("SELECT m FROM Menu m WHERE m.published BETWEEN :start_date AND :end_date ORDER BY m.published, m.restaurant.name DESC")
    List<Menu> getBetween(@Param("start_date")LocalDate start, @Param("end_date")LocalDate end);

    @Transactional(readOnly = true)
    @Query("SELECT m FROM Menu m WHERE m.published=:date ORDER BY m.published DESC")
    List<Menu> getByDate(@Param("date") LocalDate date);
}