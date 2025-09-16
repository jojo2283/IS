package com.example.inf_seq.entity.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class JwtRequest {

    private String username;
    private String password;


}
