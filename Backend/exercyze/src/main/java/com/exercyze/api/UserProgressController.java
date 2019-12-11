package com.exercyze.api;

import com.exercyze.dao.UserProgressDao;
import com.exercyze.model.UserProgress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("api/user-progress")
@RestController
public class UserProgressController {

    @Autowired
    UserProgressDao db;

    /**
     * Add a new user progress entry to the database
     * @param userProgress
     */
    @PostMapping
    public void addUserProgressEntry(@Valid @NonNull @RequestBody UserProgress userProgress){
        db.save(userProgress);
    }

    /**
     * Method to get all user progress entries in the database
     * @return list of all user progress entries createds
     */
    @GetMapping
    public List<UserProgress> getAllUserProgressEntry(){
        return db.findAll();
    }

    /**
     * Method to get all user progress entries under a specific user
     * @param userId
     * @return
     */
    @GetMapping(path = "{id}")
    public List<UserProgress> getAllUserProgressEntryById(@PathVariable int userId){
        return db.findAllByUserId(userId);
    }
}
