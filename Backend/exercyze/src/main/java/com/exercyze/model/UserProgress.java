package com.exercyze.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="user_progress")
public class UserProgress {

    /**
     * ID to identify progress entry
     */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    /**
     * ID to identify user who made progress entry
     */
    @Column(name = "user_id")
    private int userId;

    /**
     * New weight for progress
     */
    @Column(name = "new_weight")
    private int newWeight;

    /**
     * Date that the progress entry was created
     */
    @Column(name = "date_entered")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date dateEntered;

    /**
     * Constructor to create new UserProgress Entry
     * @param userId
     * @param newWeight
     * @param dateEntered
     */
    public UserProgress(int userId, int newWeight, Date dateEntered) {
        this.userId = userId;
        this.newWeight = newWeight;
        this.dateEntered = dateEntered;
    }

    /**
     * Empty constructor
     */
    public UserProgress() {
        this.userId = 0;
        this.newWeight = 0;
        this.dateEntered = null;
    }

    /**
     * Method to return UserProgress entry ID
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Method to return User Id
     * @return userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Method to return new weight entry
     * @return newWeight
     */
    public int getNewWeight() {
        return newWeight;
    }

    /**
     * Method to return date of entry
     * @return dateEntered
     */
    public Date getDateEntered() {
        return dateEntered;
    }
}
