package com.exercyze.dao;

import com.exercyze.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutDao extends JpaRepository<Workout, Integer> {
    /**
     * Database helper method to help find all workouts in a specific routine in the database
     * @param workoutRoutineId
     * @return
     */
    List<Workout> findAllByWorkoutRoutineId(int workoutRoutineId);
}
