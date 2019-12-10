package com.exercyze.dao;

import com.exercyze.model.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProgressDao extends JpaRepository<UserProgress, Integer> {
    List<UserProgress> findAllByUserId(int userId);
}
