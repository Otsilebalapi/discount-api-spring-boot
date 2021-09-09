package com.example.discountApp.Auth.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import com.example.discountApp.User.model.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.HashMap;

@Component
public class JwtUtil {

    private String secret = "secret";
    private int validity = 1000 * 60 * 60 * 10;

    private Claims extractClaims(String token)
    {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
    
    public String extractUsernameFromToken(String token)
    {
        Claims claim = extractClaims(token);
        String username = claim.getSubject();
        
        return username;
    }

    public Date extractExpirationDateFromToken(String token) 
    {
        Claims claim = extractClaims(token);
        Date expirationDate = claim.getExpiration();

        return expirationDate;
    }

    public boolean isTokenExpired(String token) {
        Date expirationDate = this.extractExpirationDateFromToken(token);
        
        boolean isExpired = expirationDate.before(new Date());

        return isExpired;
    }

    public String generateToken(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<String, Object>();
        return createToken(claims, userDetails.getUsername());
    }

    public String createToken(Map<String, Object> claims, String username)
    {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + validity))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean validateToken(String token, User userDetails)
    {
        String username = extractUsernameFromToken(token);
        boolean isTokenValid = (username.equals(userDetails.getUsername()) && !isTokenExpired(token));

        return isTokenValid;
    }


    
}
