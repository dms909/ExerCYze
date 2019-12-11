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

    /**
     * Method to add a new workout routine in the database
     * @param workoutRoutine
     */
    @PostMapping
    public void addWorkoutRoutine(@Valid @NonNull @RequestBody WorkoutRoutine workoutRoutine){
        routineDao.save(workoutRoutine);
    }

    /**
     * Method to get a workout routine by its unique id
     * @param id
     * @return workoutRoutine found by it's id
     */
    @GetMapping(path = "{id}")
    public WorkoutRoutine getWorkoutRoutineById(@PathVariable Integer id){
        WorkoutRoutine toReturn =  routineDao.findWorkoutRoutineById(id);
        return toReturn;
    }

    /**
     * Method to return all workout routines created on the server
     * @return all workout routines in database
     */
    @GetMapping
    public List<WorkoutRoutine> getAllWorkoutRoutine(){
        return routineDao.findAll();
    }

    /**
     * Method to delete a workout routine by its id
     * @param id
     */
   @DeleteMapping(path="/{id}")
    public void deleteWorkoutRoutineById(@PathVariable Integer id){
        routineDao.deleteById(id);
   }
}
