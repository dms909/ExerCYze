package com.example.exercyzefrontend.ui.workout;

public class Workout {
    private String workoutItem;

    private int sets;

    private int reps;

    public Workout(String workoutItem, int sets, int reps) {
        this.workoutItem = workoutItem;
        this.sets = sets;
        this.reps = reps;
    }

    public String getWorkoutItem() {
        return workoutItem;
    }

    public int getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
    }
}
