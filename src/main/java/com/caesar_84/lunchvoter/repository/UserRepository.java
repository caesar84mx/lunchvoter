package com.caesar_84.lunchvoter.repository;

import com.caesar_84.lunchvoter.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;

@RepositoryRestResource
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.name=:name, u.password=:password WHERE u.id=:id")
    int update(@Param("id") int id, @Param("name") String name, @Param("password") String password);

    @Secured("ROLE_ADMIN")
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.enabled=FALSE WHERE u.id=:id")
    int disable(@Param("id") int id);

    @Secured("ROLE_ADMIN")
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.enabled=TRUE WHERE u.id=:id")
    int enable(@Param("id") int id);

    @Query("SELECT u FROM User u LEFT JOIN u.roles where u.email=:email")
    User getByEmail(@Param("email") String email);
}
