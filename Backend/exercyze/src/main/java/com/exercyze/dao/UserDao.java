package com.exercyze.dao;

import com.exercyze.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    /**
     * Helper method to find a user by their username in the database
     * @param userName
     * @return
     */
    User findByUserName(String userName);

    /**
     * Helper method to find a user by their id in the database
     * @param id
     * @return
     */
    User findUserById(Integer id);
}
