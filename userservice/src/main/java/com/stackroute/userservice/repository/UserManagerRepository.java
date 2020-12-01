package com.stackroute.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.userservice.model.User;

@Repository
public interface UserManagerRepository  extends JpaRepository<User, String> {
    
    User findByUsernameAndPassword(String username, String password);
    
    List<User> findByUsername(String username);
}
