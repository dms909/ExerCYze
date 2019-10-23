package com.exercyze.dao;

import com.exercyze.model.WorkoutRoutine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRoutineDao extends JpaRepository<WorkoutRoutine, Integer> {

}