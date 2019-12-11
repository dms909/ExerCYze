package com.exercyze.api;

import com.exercyze.dao.WorkoutDao;
import com.exercyze.dao.WorkoutRoutineDao;
import com.exercyze.model.Workout;
import com.exercyze.model.WorkoutRoutine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("api/workout-routine")
@RestController
public class WorkoutRoutineController {

    @Autowired
    WorkoutRoutineDao routineDao;

    @PostMapping
    private void addWorkoutRoutine(@Valid @NonNull @RequestBody WorkoutRoutine workoutRoutine){
        routineDao.save(workoutRoutine);
    }

    @GetMapping(path = "{id}")
    private WorkoutRoutine getWorkoutRoutineById(@PathVariable Integer id){
        WorkoutRoutine toReturn =  routineDao.findWorkoutRoutineById(id);
        return toReturn;
    }

    @GetMapping
    private List<WorkoutRoutine> getAllWorkoutRoutine(){
        return routineDao.findAll();
    }

   @DeleteMapping
    private void deleteWorkoutRoutineById(@PathVariable Integer id){
        routineDao.deleteById(id);
   }
}
