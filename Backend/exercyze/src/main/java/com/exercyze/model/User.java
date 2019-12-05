package com.exercyze.model;

import javax.persistence.*;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private final Integer id;

    @Column(name = "first_name")
    private final String firstName;

    @Column(name = "last_name")
    private final String lastName;

    @Column(name = "user_name", unique = true)
    private final String userName;

    @Column(name = "password")
    private final String password;

    @Column(name = "weight")
    private double weight;

    @Column(name = "height")
    private double height;

    public User(Integer id,
                String firstName,
                String lastName,
                String userName,
                String password,
                double weight,
                double height)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.weight = weight;
        this.height = height;
    }

   public User(){
        id = null;
        firstName = null;
        lastName = null;
        userName = null;
        password = null;
        weight = 0;
        height = 0;
   }


    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public double getWeight()
    {
        return weight;
    }

    public double getHeight()
    {
        return height;
    }

    public String getPassword() {
        return password;
    }
}
