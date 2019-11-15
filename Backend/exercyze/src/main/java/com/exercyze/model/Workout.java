package com.exercyze.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="workout")
public class Workout {

    @Id
    @Column(name = "workoutName")
    private String workoutName;

    @Column(name = "reps")
    private int reps;

    @Column(name = "sets")
    private int sets;

    @Column(name = "muscleGroup")
    private String muscleGroup;

    @Column(name = "weight")
    private int weight;

    @Column(name = "workoutRoutineId")
    private int workoutRoutineId;


    public Workout(String workoutName, int reps, int sets, String muscleGroup, int weight, int workoutRoutineId) {
        this.workoutName = workoutName;
        this.reps = reps;
        this.sets = sets;
        this.muscleGroup = muscleGroup;
        this.weight = weight;
        this.workoutRoutineId = workoutRoutineId;
    }

    public Workout() {
        this.workoutName = null;
        this.reps = 0;
        this.sets = 0;
        this.muscleGroup = null;
        this.weight = 0;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public int getReps() {
        return reps;
    }

    public int getSets() {
        return sets;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public int getWeight() {
        return weight;
    }

    public int getWorkoutRoutineId() {
        return workoutRoutineId;
    }
}
