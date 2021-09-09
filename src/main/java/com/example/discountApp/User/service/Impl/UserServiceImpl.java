package com.example.discountApp.User.service.Impl;

import com.example.discountApp.User.service.IUserService;

import com.example.discountApp.User.repository.IUserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.example.discountApp.User.model.User;
import com.example.discountApp.User.model.UserType;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService, IUserService{

    
    private final IUserRepository iUserRepository;
    private final PasswordEncoder passwordEncoder;
    
    public UserServiceImpl(IUserRepository iUserRepository, PasswordEncoder passwordEncoder)
    {
        this.iUserRepository = iUserRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = Optional.ofNullable(iUserRepository.findUserByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
        return (UserDetails) user;
    }
    
    @Override
    public void addStarterUserInformation()
    {
        iUserRepository.save(addDemoUser("userEmployee@gmail.com", "secret" ,UserType.Employee, 6));
        iUserRepository.save(addDemoUser("userAffiliate@gmail.com", "secret", UserType.Affiliate, 1));
        iUserRepository.save(addDemoUser("userCustomer@gmail.com", "secret", UserType.Customer, 5));
        iUserRepository.save(addDemoUser("userCustomerNew@gmail.com", "secret", UserType.Customer, 1));
    }

    public User addDemoUser(String username, String password, UserType usertype, int numOfyearsAsUser)
    {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setUsertype(usertype);
        user.setNumOfyearsAsUser(numOfyearsAsUser);
        return user;
    }

    @Override
    public List<User> getAllUsers()
    {
        List<User> allUsers = iUserRepository.findAll();

        return allUsers;
    }

    @Override
    public void registerUser(String username, String password, UserType usertype, int numOfyearsAsUser)
    {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setUsertype(usertype);
        user.setNumOfyearsAsUser(numOfyearsAsUser);
        
        iUserRepository.save(user);
    }
}
