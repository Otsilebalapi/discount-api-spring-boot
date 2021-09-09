package com.example.discountApp.RepositoryTests;

import com.example.discountApp.User.model.User;
import com.example.discountApp.User.model.UserType;
import com.example.discountApp.User.repository.IUserRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private IUserRepository iUserRepository;

    @AfterEach
    void tearDown()
    {
        iUserRepository.deleteAll();
    }

    
    @Test
    void itShouldfindUserByUsername()
    {
        //given
        String username = "userForTest@gmail.com";
        User user = new User(username, "secret" ,UserType.Employee, 6);
        iUserRepository.save(user);

        //when
        User expected = iUserRepository.findUserByUsername(username);

        //then
        assertThat(expected.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    void itShouldNotfindUserByUsername()
    {
        //given
        String username = "userForTest@gmail.com";
        
        //when
        User expected = iUserRepository.findUserByUsername(username);

        //then
        assertThat(expected).isNull();
    }
    
}
