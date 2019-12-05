package com.exercyze.dao;

import com.exercyze.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    User findByUserName(String userName);

    User findUserById(Integer id);
}
