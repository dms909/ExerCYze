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
    private void addUserProgressEntry(@Valid @NonNull @RequestBody UserProgress userProgress){
        db.save(userProgress);
    }

    @GetMapping
    private List<UserProgress> getAllUserProgressEntry(){
        return db.findAll();
    }

    @GetMapping(path = "{id}")
    private List<UserProgress> getAllUserProgressEntryById(@PathVariable int userId){
        return db.findAllByUserId(userId);
    }
}
