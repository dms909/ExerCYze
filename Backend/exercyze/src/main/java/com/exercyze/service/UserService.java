package com.exercyze.service;

import com.exercyze.dao.UserDao;
import com.exercyze.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public int addUser(User user){
        return userDao.insertUser(user);
    }

    public List<User> getAllUsers(){
        return userDao.selectAllUsers();
    }

    public Optional<User> getUserById(UUID id){
        return userDao.selectUserById(id);
    }

    public int deleteUser(UUID id){
        return userDao.deleteUserById(id);
    }

    public int updateUser(UUID id, User newUser){
        return userDao.updateUserById(id, newUser);
    }
}
