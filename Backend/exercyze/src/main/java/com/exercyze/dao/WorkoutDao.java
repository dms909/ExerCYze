package com.exercyze.dao;

import com.exercyze.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutDao extends JpaRepository<Workout, Integer> {
    List<Workout> findAllByWorkoutRoutineId(int workoutRoutineId);
}
