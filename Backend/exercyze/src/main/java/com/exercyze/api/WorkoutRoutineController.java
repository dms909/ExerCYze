package com.exercyze.api;

import com.exercyze.dao.WorkoutRoutineDao;
import com.exercyze.model.Workout;
import com.exercyze.model.WorkoutRoutine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

public class WorkoutRoutineController {

    @Autowired
    WorkoutRoutineDao db;

    @PostMapping
    public void addWorkoutRoutine(@Valid @NonNull @RequestBody WorkoutRoutine workoutRoutine){
        db.save(workoutRoutine);
    }

    @GetMapping
    public List<WorkoutRoutine> getAllWorkoutRoutine(){
        return db.findAll();
    }

   /* @GetMapping(path = "{id}")
    public List<Workout> getAllWorkoutsByWorkoutRoutineId(@PathVariable Integer id){
        Optional<WorkoutRoutine> toReturn =  db.findById(id);
        toReturn.getWorkoutList();
    }*/
}
