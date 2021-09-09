package com.example.discountApp.User.service;

import com.example.discountApp.User.model.User;
import com.example.discountApp.User.model.UserType;

import java.util.List;

public interface IUserService {

    void registerUser(String username, String password, UserType usertype, int numOfyearsAsUser);
    void addStarterUserInformation();
    List<User> getAllUsers();
    
}
