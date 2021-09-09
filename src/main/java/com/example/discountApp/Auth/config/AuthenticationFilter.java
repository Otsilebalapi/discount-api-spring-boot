package com.example.discountApp.Auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.discountApp.Auth.util.JwtUtil;
import com.example.discountApp.User.model.User;
import com.example.discountApp.User.service.Impl.UserServiceImpl;


public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{

    private String headerString = "Authorization";

    @Autowired
    private UserServiceImpl userService;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
    throws IOException, ServletException 
    {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String authToken = httpRequest.getHeader(headerString);
        String username = null;
        if(authToken != null)
        {
             username = jwtUtil.extractUsernameFromToken(authToken);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                  User userDetails = (User) userService.loadUserByUsername(username);
            if (this.jwtUtil.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                        userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                ((HttpServletResponse) response).sendError(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED.value());
                return;
            }
        }
        
        chain.doFilter(request, response);
    }

    
    
}
