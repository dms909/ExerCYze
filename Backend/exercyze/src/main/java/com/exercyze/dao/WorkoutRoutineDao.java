package com.exercyze.dao;

import com.exercyze.model.WorkoutRoutine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRoutineDao extends JpaRepository<WorkoutRoutine, Integer> {
    WorkoutRoutine findWorkoutRoutineById(Integer id);
}