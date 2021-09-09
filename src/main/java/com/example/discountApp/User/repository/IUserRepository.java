package com.example.discountApp.User.repository;

import java.util.List;

import com.example.discountApp.User.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IUserRepository extends JpaRepository<User, String>{

    User findUserByUsername(String username);
    
}
