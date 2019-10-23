package com.exercyze.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="user")
public class WorkoutRoutine {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    private String workoutRoutineName;

    private String workoutRoutineCreator;

    private List<Workout> workoutList;

    public WorkoutRoutine(String workoutRoutineName, String workoutRoutineCreator) {
        this.workoutRoutineName = workoutRoutineName;
        this.workoutRoutineCreator = workoutRoutineCreator;
        this.workoutList = null;
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

    public List<Workout> getWorkoutList() {
        return workoutList;
    }
}
