package com.exercyze.api;

import com.exercyze.dao.UserDao;
import com.exercyze.model.User;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping("api/user")
@RestController
public class UserController {

    @Autowired
    UserDao db;

    @PostMapping
    public void addUser(@Valid @NonNull @RequestBody User user){
        if(getUserByUserName(user.getUserName()) == null){
            db.save(user);
            return;
        }
    }

    @GetMapping
    public List<User> getAllUser(){
        return db.findAll();
    }

    @GetMapping(path = "{id}")
    public User getUserById(@PathVariable Integer id){
        return db.findUserById(id);
    }

    @GetMapping(path = "userName")
    public User getUserByUserName(@RequestParam("userName") String userName){
        return db.findByUserName(userName);
    }

    @DeleteMapping(path="{id}")
    public void deleteUserById(@PathVariable("id") Integer id){
        db.deleteById(id);
    }

    @GetMapping(path="authenticate")
    public boolean authenticateUserByUserName(@RequestBody HashMap<String, String> user){
        System.out.println(user);
        System.out.println(user);
        System.out.println(user);
        /*User toAuthenticate = db.findByUserName(user.get("userName"));
        if(!toAuthenticate.getPassword().equals(user.get("password"))){
            return false;
        }*/
        return true;
    }

    /*@PutMapping(path="{id}")
    public void updateUserById(@PathVariable("id") Integer id, @Valid @NonNull @RequestBody User userToUpdate){
        Optional<User> oldUser = db.findById(id);
        oldUser.n
    }*/

}
