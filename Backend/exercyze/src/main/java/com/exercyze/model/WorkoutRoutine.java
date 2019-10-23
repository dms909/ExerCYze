package com.exercyze.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="workout_routine")
public class WorkoutRoutine {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "workout_routine_name")
    private String workoutRoutineName;

    @Column(name = "workout_routine_creator")
    private String workoutRoutineCreator;

    public WorkoutRoutine(Integer id, String workoutRoutineName, String workoutRoutineCreator) {
        this.id = id;
        this.workoutRoutineName = workoutRoutineName;
        this.workoutRoutineCreator = workoutRoutineCreator;
        //this.workoutList = null;
    }

    public WorkoutRoutine(){
        this.id = null;
        this.workoutRoutineName = null;
        this.workoutRoutineCreator = null;
    }

    public Integer getId() {
        return id;
    }

    public String getWorkoutRoutineName() {
        return workoutRoutineName;
    }

    public String getWorkoutRoutineCreator() {
        return workoutRoutineCreator;
    }

    /*public List<Workout> getWorkoutList() {
        return workoutList;
    }*/
}
