package com.example.inf_seq.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.util.HtmlUtils;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class UserDTO {
    private String username;
    private String email;
    private Set<Role> roles;


    public UserDTO(UserEntity user) {
        this.username = HtmlUtils.htmlEscape(user.getUsername());
        this.email = HtmlUtils.htmlEscape(user.getEmail());
        this.roles = user.getRoles();
    }

    public Set<Role> getRoles() {
        return roles == null ? null : new HashSet<>(roles);
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles == null ? null : new HashSet<>(roles);
    }
}
