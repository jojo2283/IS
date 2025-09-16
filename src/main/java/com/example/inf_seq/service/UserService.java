package com.example.inf_seq.service;

import com.example.inf_seq.entity.UserEntity;
import com.example.inf_seq.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;



    @Transactional
    public void save(UserEntity newUser) {
        userRepository.save(newUser);
    }



    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
