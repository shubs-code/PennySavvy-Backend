package com.project.spring.datajpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.spring.datajpa.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

    @Query("SELECT u FROM User u ORDER BY u.score DESC")
    List<User> findAllUsersSortedByScoreDesc();
    
}
