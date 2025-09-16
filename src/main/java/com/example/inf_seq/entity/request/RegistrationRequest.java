package com.example.inf_seq.entity.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class RegistrationRequest {
    private String username;
    private String email;
    private String password;
}
