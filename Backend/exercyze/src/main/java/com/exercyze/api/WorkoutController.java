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
    private void addWorkoutToRoutine(@RequestBody Workout workout){
        db.save(workout);
    }

    @GetMapping(path="getRoutine/{id}")
    private List<Workout> getWorkoutsByRoutineId(@PathVariable int id){
        return db.findAllByWorkoutRoutineId(id);
    }

    @DeleteMapping(path="{id}")
    private void deleteWorkoutByWorkoutId(@PathVariable int id){
        db.deleteById(id);
    }
}
