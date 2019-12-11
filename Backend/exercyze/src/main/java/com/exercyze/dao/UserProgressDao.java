package com.exercyze.dao;

import com.exercyze.model.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProgressDao extends JpaRepository<UserProgress, Integer> {
    /**
     * Helper method to find all users by an id in the database
     * @param userId
     * @return
     */
    List<UserProgress> findAllByUserId(int userId);
}
