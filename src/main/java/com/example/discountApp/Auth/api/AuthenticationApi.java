package com.example.discountApp.Auth.api;

import java.util.List;

import com.example.discountApp.Auth.models.AuthenticationRequest;
import com.example.discountApp.Auth.models.AuthenticationResponse;
import com.example.discountApp.Auth.util.JwtUtil;
import com.example.discountApp.User.service.Impl.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthenticationApi {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/generateToken")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception
    {
        try {

            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
            
        } catch (BadCredentialsException e) {
            throw new Exception("Wrong username or password", e);
        }

        final UserDetails user = userService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtUtil.generateToken(user);

        return ResponseEntity.ok(new AuthenticationResponse(token));
        
    }
}
