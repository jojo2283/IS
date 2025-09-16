package com.example.inf_seq.controller;

import com.example.inf_seq.entity.UserDTO;
import com.example.inf_seq.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class DataController {

    private final UserRepository userRepository;


    @GetMapping("/data")
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> users = userRepository.findAll()
                .stream()
                .map(UserDTO::new)
                .toList();

        return ResponseEntity.ok(users);

    }
}