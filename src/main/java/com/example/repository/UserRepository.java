package com.example.repository;

import com.example.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsernameAndPassword(String username,String password);
    @Query("select u.username from User u ORDER BY u.username")
    List<String> findAllUsernames();

    Optional<User>  findByUsername(String username);
}
