package com.exercyze.model;


import javax.persistence.*;

@Entity
@Table(name="workout")
public class Workout {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int workoutId;

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


    public Workout(int workoutId, String workoutName, int reps, int sets, String muscleGroup, int weight, int workoutRoutineId) {
        this.workoutId = workoutId;
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

    public int getWorkoutId() {
        return workoutId;
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
