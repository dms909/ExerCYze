package com.exercyze.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="user_workout")
public class UserProgress {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "new_weight")
    private int newWeight;

    @Column(name = "date_entered")
    private Date dateEntered;

    public UserProgress(int userId, int newWeight, Date dateEntered) {
        this.userId = userId;
        this.newWeight = newWeight;
        this.dateEntered = dateEntered;
    }

    public UserProgress() {
        this.userId = 0;
        this.newWeight = 0;
        this.dateEntered = null;
    }

    public Integer getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getNewWeight() {
        return newWeight;
    }

    public Date getDateEntered() {
        return dateEntered;
    }
}
