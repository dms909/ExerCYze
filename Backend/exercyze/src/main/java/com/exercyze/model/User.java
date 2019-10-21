package com.exercyze.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private final Integer id;

    @Column(name = "firstName")
    private final String firstName;

    @Column(name = "lastName")
    private final String lastName;

    //add unique
    @Column(name = "userName")
    private final String userName;

    @Column(name = "password")
    private final String password;

    @Column(name = "weight")
    private double weight;

    @Column(name = "height")
    private double height;

    private final String test;

    public User(Integer id,
                String firstName,
                String lastName,
                String userName,
                String password,
                double weight,
                double height,
                String test)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.weight = weight;
        this.height = height;
        this.test = test;
    }

   public User(){
        id = null;
        firstName = null;
        lastName = null;
        userName = null;
        password = null;
        weight = 0;
        height = 0;
        test = "hi there";
   }


    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName + "hello there!";
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

    public String getTest() {return test;}
}
