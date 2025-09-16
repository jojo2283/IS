package com.example.inf_seq.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {


    USER("USER");

    private final String value;

    @Override
    public String getAuthority() {
        return value;
    }

}
