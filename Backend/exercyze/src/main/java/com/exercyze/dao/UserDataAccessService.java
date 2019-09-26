package com.exercyze.dao;

import com.exercyze.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("dao")
public class UserDataAccessService implements UserDao {
    private static List<User> DB = new ArrayList<>();

    @Override
    public int insertUser(UUID id, User user) {
        DB.add(new User(id, user.getFirstName(), user.getLastName(), user.getUserName(),user.getWeight(),user.getHeight()));
        return 1;
    }

    @Override
    public List<User> selectAllUsers() {
        return DB;
    }

    @Override
    public Optional<User> selectUserById(UUID id) {
        return DB.stream().
                filter(user -> user.getId().equals(id)).
                findFirst();
    }

    @Override
    public int deleteUserById(UUID id) {
        Optional<User> userToDelete = selectUserById(id);
        if(!userToDelete.isPresent()){
            return 0;
        }
        DB.remove(userToDelete.get());

        return 1;
    }

    @Override
    public int updateUserById(UUID id, User user) {
        return selectUserById(id)
                .map(p -> {
                    int indexOfUserToUpdate = DB.indexOf(p);
                    if(indexOfUserToUpdate >= 0){
                        DB.set(indexOfUserToUpdate,
                                new User(
                                        id,
                                        user.getFirstName(),
                                        user.getLastName(),
                                        user.getUserName(),
                                        user.getWeight(),
                                        user.getHeight()
                        ));

                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }

}
