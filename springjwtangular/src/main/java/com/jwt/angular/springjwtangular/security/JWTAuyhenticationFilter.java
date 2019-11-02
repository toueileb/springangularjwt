package com.jwt.angular.springjwtangular.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwt.angular.springjwtangular.entities.AppUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JWTAuyhenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JWTAuyhenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        AppUser appUser = null;
        try {
            appUser = new ObjectMapper().readValue(request.getInputStream(), AppUser.class);


        } catch (Exception e) {
            System.out.println("Error " + e.toString());
        }
        System.out.println("*********************************");
        System.out.println("username :" + appUser.getUsername());
        System.out.println("password :" + appUser.getPassword());
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        appUser.getUsername(), appUser.getPassword()
                )
        );

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User springUser = (User) authResult.getPrincipal();
        String jwt = Jwts.builder()
                .setSubject(springUser.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstaints.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstaints.SECRET)
                .claim("roles", springUser.getAuthorities())
                .compact();
        response.addHeader(SecurityConstaints.HEADER_STRING, SecurityConstaints.TOKEN_PREFIX + jwt);

    }
}
