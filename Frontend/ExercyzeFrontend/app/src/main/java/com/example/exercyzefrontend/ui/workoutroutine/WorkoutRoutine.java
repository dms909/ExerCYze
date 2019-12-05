package com.example.exercyzefrontend.ui.workoutroutine;

public class WorkoutRoutine {

    /**
     * variable to hold the name of a workout routine
     */
    private String workoutRoutineName;

    /**
     * a variable that holds the ID of a workout in the backend per the specified routine
     */
    private int workoutRoutineID;

    /**
     * will hold the name of who created the workout to identify the workouts on the backend
     */
    private String workoutRoutineCreator;

    /**
     * method that creates a workout routine object
     * @param workoutRoutineID
     * @param workoutRoutineName
     * @param workoutCreator
     */
    public WorkoutRoutine(int workoutRoutineID, String workoutRoutineName, String workoutCreator) {
        this.workoutRoutineID = workoutRoutineID;
        this.workoutRoutineName = workoutRoutineName;
        this.workoutRoutineCreator = workoutCreator;
    }

    /**
     * a method when creating a null workout routine object
     */
    public WorkoutRoutine() {
        this.workoutRoutineID = 0;
        this.workoutRoutineName = null;
        this.workoutRoutineCreator = null;
    }

    /**
     *
     * @return will return the workout routine ID for the workout routine object
     */
    public int getWorkoutRoutineID() {
        return workoutRoutineID;
    }

    /**
     *
     * @return will retunr the name of the workout routine object
     */
    public String getWorkoutRoutineName() {
        return workoutRoutineName;
    }

    /**
     *
     * @return returns the creator of the workout routine
     */
    public String getWorkoutRoutineCreator() {
        return workoutRoutineCreator;
    }
}
