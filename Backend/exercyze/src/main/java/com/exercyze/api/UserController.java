package com.exercyze.api;

import com.exercyze.dao.UserDao;
import com.exercyze.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("api/user")
@RestController
public class UserController {

    @Autowired
    UserDao db;

    @PostMapping
    public void addUser(@Valid @NonNull @RequestBody User user){
        db.save(user);
    }

    @GetMapping
    public List<User> getAllUser(){
        return db.findAll();
    }

    @GetMapping(path = "{id}")
    public Optional<User> getUserById(@PathVariable Integer id){
        return db.findById(id);
    }

    @DeleteMapping(path="{id}")
    public void deleteUserById(@PathVariable("id") Integer id){
        db.deleteById(id);
    }

    /*@PutMapping(path="{id}")
    public void updateUserById(@PathVariable("id") Integer id, @Valid @NonNull @RequestBody User userToUpdate){
        Optional<User> oldUser = db.findById(id);
        oldUser.n
    }*/

}
