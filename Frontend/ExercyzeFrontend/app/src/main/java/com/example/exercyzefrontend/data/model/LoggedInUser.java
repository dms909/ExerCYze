package com.example.exercyzefrontend.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String userName;

    public LoggedInUser(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

}
