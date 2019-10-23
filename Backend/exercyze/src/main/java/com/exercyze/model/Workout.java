package com.exercyze.model;



public class Workout {

    private String workoutName;

    private int reps;

    private int sets;

    private String muscleGroup;

    private int weight;


    public Workout(String workoutName, int reps, int sets, String muscleGroup, int weight) {
        this.workoutName = workoutName;
        this.reps = reps;
        this.sets = sets;
        this.muscleGroup = muscleGroup;
        this.weight = weight;
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
}
