package com.example.inf_seq.entity.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtResponse {

    private  final String TYPE = "Bearer";
    private String accessToken;


}