package com.example.inf_seq.controller;


import com.example.inf_seq.entity.UserEntity;
import com.example.inf_seq.entity.request.JwtRequest;
import com.example.inf_seq.entity.request.RegistrationRequest;
import com.example.inf_seq.entity.response.JwtResponse;
import com.example.inf_seq.repository.UserRepository;
import com.example.inf_seq.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> registration(@RequestBody RegistrationRequest regRequest) {

        final JwtResponse token = authService.register(regRequest);
        return ResponseEntity.ok(token);

    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authRequest) {
        final JwtResponse token = authService.login(authRequest);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/data")
    public ResponseEntity<List<UserEntity>> getUsers() {
        List<UserEntity> users = userRepository.findAll();

        return ResponseEntity.ok(users);
    }
    @GetMapping("/me")
    public ResponseEntity<Map<String, Object>> getMe(@RequestHeader("Authorization") String authHeader) {
        Map<String, Object> info = authService.getUserByToken(authHeader);
       return ResponseEntity.ok(info);
    }

//    @PostMapping("token")
//    public ResponseEntity<JwtResponse> getNewAccessToken(@RequestBody RefreshJwtRequest request) {
//        final JwtResponse token = authService.getAccessToken(request.getRefreshToken());
//        return ResponseEntity.ok(token);
//    }
//
//    @PostMapping("refresh")
//    public ResponseEntity<JwtResponse> getNewRefreshToken(@RequestBody RefreshJwtRequest request) {
//        final JwtResponse token = authService.refresh(request.getRefreshToken());
//        return ResponseEntity.ok(token);
//    }

}