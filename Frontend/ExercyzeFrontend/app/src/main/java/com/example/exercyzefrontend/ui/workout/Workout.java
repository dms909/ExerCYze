package com.example.exercyzefrontend.ui.workout;

public class Workout {
    /**
     * This will be the workout item that will be given a name when
     * adding to the workout routine i.e. bench, push-ups, sit-ups
     */
    private String workoutItem;

    /**
     * Variable to hold the amount of sets for a workout item
     */
    private int sets;

    /**
     * Variable to hold the amount of reps per set of a workout item
     */
    private int reps;

    public Workout(String workoutItem, int sets, int reps) {
        this.workoutItem = workoutItem;
        this.sets = sets;
        this.reps = reps;
    }

    /**
     *
     * @return ill return the name of the workout item
     */
    public String getWorkoutItem() {
        return workoutItem;
    }

    /**
     *
     * @return will return the amount of sets per workout item
     */
    public int getSets() {
        return sets;
    }

    /**
     *
     * @return will return the amount of reps per set of a given workout item
     */
    public int getReps() {
        return reps;
    }
}
