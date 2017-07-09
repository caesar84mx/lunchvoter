package com.caesar_84.lunchvoter.repository;

import com.caesar_84.lunchvoter.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RepositoryRestResource
@Transactional
public interface VoteRepository extends JpaRepository<Vote, Integer> {
    @Transactional(readOnly = true)
    @Query("SELECT v FROM Vote v WHERE v.menu.id=:menu_id AND v.user.id=:user_id")
    Vote getForMenuAndUser(@Param("menu_id") int menuId, @Param("user_id") int userId);

    @RestResource(path = "by-menu")
    @Transactional(readOnly = true)
    @Query("SELECT v FROM Vote v WHERE v.menu.id=:menu_id")
    List<Vote> getByMenu(@Param("menu_id") int menuId);
}