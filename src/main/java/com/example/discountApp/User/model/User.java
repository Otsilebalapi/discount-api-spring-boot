package com.example.discountApp.User.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
//@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User implements UserDetails{

    
    @Id
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserType usertype;

    private int numOfyearsAsUser;

    public User()
    {
        
    }

    public User(String username, String password, UserType usertype, int numOfyearsAsUser)
    {
        this.username = username;
        this.password = password;
        this.usertype = usertype;
        this.numOfyearsAsUser = numOfyearsAsUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

}
