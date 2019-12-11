package com.exercyze.dao;

import com.exercyze.model.WorkoutRoutine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRoutineDao extends JpaRepository<WorkoutRoutine, Integer> {
    /**
     * Database method to help find a workoutRoutine by id in the database
     * @param id
     * @return
     */
    WorkoutRoutine findWorkoutRoutineById(Integer id);
}