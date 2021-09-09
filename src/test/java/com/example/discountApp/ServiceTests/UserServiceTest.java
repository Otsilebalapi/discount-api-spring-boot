package com.example.discountApp.ServiceTests;

import static org.mockito.Mockito.verify;

import com.example.discountApp.User.model.User;
import com.example.discountApp.User.model.UserType;
import com.example.discountApp.User.repository.IUserRepository;
import com.example.discountApp.User.service.Impl.UserServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock private IUserRepository iUserRepository;
    private UserServiceImpl userServiceImpl;
    private PasswordEncoder passwordEncoder;
    
    @BeforeEach
    void setUp()
    {
        userServiceImpl = new UserServiceImpl(iUserRepository,passwordEncoder);
        iUserRepository.deleteAll();
    }


    @Test
    void itShouldAddNewUser()
    {
        //given
        String username = "userTesting@gmail.com";
        User user = new User(username, "secret" ,UserType.Employee, 6);

        //when
        userServiceImpl.registerUser(username, "secret" ,UserType.Employee, 6);
        
        //then
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);

        verify(iUserRepository).save(userArgumentCaptor.capture());

        User capturedUser = userArgumentCaptor.getValue();

        assertThat(capturedUser.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    void itShouldgetAllUsers()
    {
        //when
        userServiceImpl.getAllUsers();
        
        //then
        verify(iUserRepository).findAll();
    }
    
}
