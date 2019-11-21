package com.example.exercyzefrontend.ui.workoutroutine;

public class WorkoutRoutine {

    private String workoutRoutineName;

    private int workoutRoutineID;

    private String workoutRoutineCreator;

    public WorkoutRoutine(int workoutRoutineID, String workoutRoutineName, String workoutCreator) {
        this.workoutRoutineID = workoutRoutineID;
        this.workoutRoutineName = workoutRoutineName;
        this.workoutRoutineCreator = workoutCreator;
    }
    public WorkoutRoutine() {
        this.workoutRoutineID = 0;
        this.workoutRoutineName = null;
        this.workoutRoutineCreator = null;
    }

    public int getWorkoutRoutineID() {
        return workoutRoutineID;
    }

    public String getWorkoutRoutineName() {
        return workoutRoutineName;
    }

    public String getWorkoutRoutineCreator() {
        return workoutRoutineCreator;
    }
}
