package com.exercyze.api;

import com.exercyze.dao.WorkoutDao;
import com.exercyze.model.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/workout")
@RestController
public class WorkoutController {

    @Autowired
    WorkoutDao db;

    @PostMapping(path="addWorkout")
    public void addWorkoutToRoutine(@RequestBody Workout workout){
        db.save(workout);
    }

    @GetMapping(path="getRoutine/{id}")
    public List<Workout> getWorkoutsByRoutineId(@PathVariable int id){
        return db.findAllByWorkoutRoutineId(id);
    }
}