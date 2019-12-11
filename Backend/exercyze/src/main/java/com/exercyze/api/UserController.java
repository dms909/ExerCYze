package com.exercyze.api;

import com.exercyze.dao.UserDao;
import com.exercyze.model.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("api/user")
@RestController
public class UserController {

    @Autowired
    UserDao db;

    /**
     * Method to add a new user to the database
     * @param user
     */
    @PostMapping
    private void addUser(@Valid @NonNull @RequestBody User user){
        if(getUserByUserName(user.getUserName()) == null){
            db.save(user);
            return;
        }
    }

    /**
     * Method to return a list of all users
     * @return list of all users in the database
     */
    @GetMapping
    private List<User> getAllUser(){
        return db.findAll();
    }

    /**
     * Method to find a user by an id and return all of that user's data
     * @param id
     * @return found user
     */
    @GetMapping(path = "{id}")
    private User getUserById(@PathVariable Integer id){
        return db.findUserById(id);
    }

    /**
     * Method to find a user by a user name and return all their data
     * @param userName
     * @return found user
     */
    @GetMapping(path = "userName")
    private User getUserByUserName(@RequestParam("userName") String userName){
        return db.findByUserName(userName);
    }

    /**
     * Delete a user
     * @param id
     */
    @DeleteMapping(path="{id}")
    private void deleteUserById(@PathVariable("id") Integer id){
        db.deleteById(id);
    }

    /**
     * Method used on sign in.
     * @param userName
     * @param password
     * @return
     */
    @PostMapping(path="authenticate")
    private @ResponseBody String authenticateUserByUserName(@RequestParam("userName") String userName, @RequestBody String password){
        JSONObject jsonObject = null;

        //Check if a user used a valid username and password. If they have, they will receive a valid login
        try {
            jsonObject = new JSONObject(password);
            User toAuthenticate = db.findByUserName(userName);
            if(!toAuthenticate.getPassword().equals(jsonObject.getString("password"))){
                jsonObject.put("authenticated", false);
            }
            else{
                jsonObject.put("authenticated", true);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}
