package com.example.inf_seq.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_entity")
public class UserEntity implements UserDetails {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private Set<Role> roles;

    public Set<Role> getRoles() {
        return roles == null ? null : new HashSet<>(roles);
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles == null ? null : new HashSet<>(roles);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles == null ? List.of() : new ArrayList<>(roles);
    }

    @Override
    public String getUsername() {
        return username;
    }
}