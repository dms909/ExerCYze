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

    @PostMapping
    public void addUserProgressEntry(@Valid @NonNull @RequestBody UserProgress userProgress){
        db.save(userProgress);
    }

    @GetMapping
    public List<UserProgress> getAllUserProgressEntry(){
        return db.findAll();
    }

    @GetMapping
    public List<UserProgress> getAllUserProgressEntryById(int userId){
        return db.findAllByUserId(userId);
    }
}
