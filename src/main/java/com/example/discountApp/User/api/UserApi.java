package com.example.discountApp.User.api;

import java.util.List;

import com.example.discountApp.User.model.User;
import com.example.discountApp.User.repository.IUserRepository;
import com.example.discountApp.User.service.IUserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(path = "/users")
public class UserApi {
    
    IUserRepository iUserRepository;
    IUserService iUserService;
    
    public UserApi(IUserRepository iUserRepository, IUserService iUserService)
    {
        this.iUserRepository = iUserRepository;
        this.iUserService = iUserService;
        iUserService.addStarterUserInformation();

    }

@GetMapping("/all")
public List<User> getAllUsers()
{
    return iUserService.getAllUsers();
}

@PostMapping("/register")
public void registeUser()
{
    
}

}
