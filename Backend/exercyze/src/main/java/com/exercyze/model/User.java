package com.exercyze.model;

import javax.persistence.*;

@Entity
@Table(name="user")
public class User {

    /**
     * Unique id assigned to each user
     */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private final Integer id;

    /**
     * Users first name
     */
    @Column(name = "first_name")
    private final String firstName;

    /**
     * Users last name
     */
    @Column(name = "last_name")
    private final String lastName;

    /**
     * Users unique username
     */
    @Column(name = "user_name", unique = true)
    private final String userName;

    /**
     * Users password to sign into their account
     */
    @Column(name = "password")
    private final String password;

    /**
     * Weight of a user to keep track of health and progress
     */
    @Column(name = "weight")
    private double weight;

    /**
     * Users height to look at progress and health
     */
    @Column(name = "height")
    private double height;

    /**
     * Constructor for a user class
     * @param id
     * @param firstName
     * @param lastName
     * @param userName
     * @param password
     * @param weight
     * @param height
     */
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

    /**
     * Default constructor for a User
     */
   public User(){
        id = null;
        firstName = null;
        lastName = null;
        userName = null;
        password = null;
        weight = 0;
        height = 0;
   }

    /**
     * Getter method to return the unique id of a user
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Getter method to return a users first name
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Getter method to return a users last name
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Getter method to return a users user name
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Getter method to return a users weight
     * @return weight
     */
    public double getWeight()
    {
        return weight;
    }

    /**
     * Getter method to return a users height
     * @return height
     */
    public double getHeight()
    {
        return height;
    }

    /**
     * Getter method to return a users password(shhhhh)
     * @return password
     */
    public String getPassword() {
        return password;
    }
}
