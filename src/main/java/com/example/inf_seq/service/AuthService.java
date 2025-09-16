package com.example.inf_seq.service;

import com.example.inf_seq.config.JwtProvider;
import com.example.inf_seq.entity.Role;
import com.example.inf_seq.entity.UserEntity;
import com.example.inf_seq.entity.request.JwtRequest;
import com.example.inf_seq.entity.request.RegistrationRequest;
import com.example.inf_seq.entity.response.JwtResponse;
import com.example.inf_seq.exception.AuthException;
import com.example.inf_seq.exception.UserAlreadyExistException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public JwtResponse register(@NonNull RegistrationRequest regRequest) {
        String username = null;
        try {
            username = userService.loadUserByUsername(regRequest.getUsername()).getUsername();
        } catch (UsernameNotFoundException ignored) {

        }
        if (username != null) {
            throw new UserAlreadyExistException("User already exist");
        }

        String hashedPassword = passwordEncoder.encode(regRequest.getPassword());


        UserEntity newUser = new UserEntity();
        newUser.setUsername(regRequest.getUsername());
        newUser.setPassword(hashedPassword);
        newUser.setEmail(regRequest.getEmail());


        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        newUser.setRoles(roles);
        userService.save(newUser);


        final String accessToken = jwtProvider.generateAccessToken(newUser);


        return new JwtResponse(accessToken);
    }

    public JwtResponse login(@NonNull JwtRequest authRequest) {
        final UserDetails user = userService.loadUserByUsername(authRequest.getUsername());

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }



        if (passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            final String accessToken = jwtProvider.generateAccessToken((UserEntity) user);

            return new JwtResponse(accessToken);
        } else {
            throw new AuthException("Wrong password");
        }
    }

    public  Map<String, Object> getUserByToken(String token){
        if (token == null || !token.startsWith("Bearer ")) {
            throw new AuthException("Token is missing or invalid");
        }

         token = token.substring(7);

        if (!jwtProvider.validateAccessToken(token)) {
            throw new AuthException("Token is missing or invalid");
        }

        var claims = jwtProvider.getAccessClaims(token);

        Map<String, Object> response = new HashMap<>();
        response.put("username", claims.getSubject());
        response.put("roles", claims.get("roles"));
        response.put("expiration", claims.getExpiration());

         return response;
    }



}