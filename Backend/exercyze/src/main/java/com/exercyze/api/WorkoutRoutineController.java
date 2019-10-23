package com.exercyze.api;

import com.exercyze.dao.WorkoutRoutineDao;
import com.exercyze.model.Workout;
import com.exercyze.model.WorkoutRoutine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("api/workout-routine")
@RestController
public class WorkoutRoutineController {

    @Autowired
    WorkoutRoutineDao db;

    @PostMapping
    public void addWorkoutRoutine(@Valid @NonNull @RequestBody WorkoutRoutine workoutRoutine){
        db.save(workoutRoutine);
    }

    @GetMapping(path = "{id}")
    public WorkoutRoutine getWorkoutRoutineById(@PathVariable Integer id){
        WorkoutRoutine toReturn =  db.findWorkoutRoutineById(id);
        return toReturn;
    }

    @GetMapping
    public List<WorkoutRoutine> getAllWorkoutRoutine(){
        return db.findAll();
    }

   /*@GetMapping(path = "{id}")
    public List<Workout> getAllWorkoutsByWorkoutRoutineId(@PathVariable Integer id){
        WorkoutRoutine toReturn =  db.findWorkoutRoutineById(id);
        return toReturn.getWorkoutList();
    }*/
}
