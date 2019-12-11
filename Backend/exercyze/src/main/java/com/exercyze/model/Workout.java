package com.exercyze.model;


import javax.persistence.*;

@Entity
@Table(name="workout")
public class Workout {

    /**
     * Unique id assigned to every workout
     */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int workoutId;

    /**
     * Workout name that is assigned by a user to every workout
     */
    @Column(name = "workoutName")
    private String workoutName;

    /**
     * Amount of reps assigned a specific workout
     */
    @Column(name = "reps")
    private int reps;

    /**
     * Amount of sets assigned to a specific workout
     */
    @Column(name = "sets")
    private int sets;

    /**
     * Muscle group affected by the workout
     */
    @Column(name = "muscleGroup")
    private String muscleGroup;

    /**
     * The amount of weight assigned to a specific workout
     */
    @Column(name = "weight")
    private int weight;

    /**
     * The workout routine that the workout is tied to
     */
    @Column(name = "workoutRoutineId")
    private int workoutRoutineId;


    /**
     * Constructor for a specific workout
     * @param workoutId
     * @param workoutName
     * @param reps
     * @param sets
     * @param muscleGroup
     * @param weight
     * @param workoutRoutineId
     */
    public Workout(int workoutId, String workoutName, int reps, int sets, String muscleGroup, int weight, int workoutRoutineId) {
        this.workoutId = workoutId;
        this.workoutName = workoutName;
        this.reps = reps;
        this.sets = sets;
        this.muscleGroup = muscleGroup;
        this.weight = weight;
        this.workoutRoutineId = workoutRoutineId;
    }

    /**
     * Workout default constructor
     */
    public Workout() {
        this.workoutName = null;
        this.reps = 0;
        this.sets = 0;
        this.muscleGroup = null;
        this.weight = 0;
    }

    /**
     * Getter method to return unique Id
     * @return workoutId
     */
    public int getWorkoutId() {
        return workoutId;
    }

    /**
     * Getter method to return workout name
     * @return workoutName
     */
    public String getWorkoutName() {
        return workoutName;
    }

    /**
     * Getter method to return amount of reps in a workout
     * @return reps
     */
    public int getReps() {
        return reps;
    }

    /**
     * Getter method to return amount of sets in a workout
     * @return sets
     */
    public int getSets() {
        return sets;
    }

    /**
     * Getter method to return muscle affected by a specific workout
     * @return muscleGroup
     */
    public String getMuscleGroup() {
        return muscleGroup;
    }

    /**
     * Getter method to return amount of weight lifted during a specific workout
     * @return weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Getter method to return the id of the workout routine that the
     * specific workout is a member of
     * @return workoutRoutineId
     */
    public int getWorkoutRoutineId() {
        return workoutRoutineId;
    }
}
