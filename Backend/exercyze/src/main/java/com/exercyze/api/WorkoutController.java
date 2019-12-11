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

    /**
     * Method to add a new workout to a routine
     * @param workout
     */
    @PostMapping(path="addWorkout")
    public void addWorkoutToRoutine(@RequestBody Workout workout){
        db.save(workout);
    }

    /**
     * Method to return a full workout routine
     * @param id
     * @return a list of workouts with the same routine id
     */
    @GetMapping(path="getRoutine/{id}")
    public List<Workout> getWorkoutsByRoutineId(@PathVariable int id){
        return db.findAllByWorkoutRoutineId(id);
    }

    /**
     * Method to delete a whole routine
     * @param id
     */
    @DeleteMapping(path="{id}")
    private void deleteWorkoutByWorkoutId(@PathVariable int id){
        db.deleteById(id);
    }
}
