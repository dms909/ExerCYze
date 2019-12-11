package com.exercyze.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="workout_routine")
public class WorkoutRoutine {

    /**
     * Unique ID for all workout routines
     */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    /**
     * Name for workout routine that user assigns
     */
    @Column(name = "workout_routine_name")
    private String workoutRoutineName;

    /**
     * User who created the specific workout routine
     */
    @Column(name = "workout_routine_creator")
    private String workoutRoutineCreator;


    /**
     * Constructor for WorkoutRoutine class
     * @param id
     * @param workoutRoutineName
     * @param workoutRoutineCreator
     */
    public WorkoutRoutine(Integer id, String workoutRoutineName, String workoutRoutineCreator) {
        this.id = id;
        this.workoutRoutineName = workoutRoutineName;
        this.workoutRoutineCreator = workoutRoutineCreator;
    }

    /**
     * Default constructor for WorkoutRoutine class
     */
    public WorkoutRoutine(){
        this.id = null;
        this.workoutRoutineName = null;
        this.workoutRoutineCreator = null;
    }

    /**
     * Method that returns a specific WorkoutRoutine's ID
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Method that returns a workout routines name
     * @return workoutRoutineName
     */
    public String getWorkoutRoutineName() {
        return workoutRoutineName;
    }

    /**
     * Method that returns the workout routine's creator
     * @return workoutRoutineCreator
     */
    public String getWorkoutRoutineCreator() {
        return workoutRoutineCreator;
    }
}
