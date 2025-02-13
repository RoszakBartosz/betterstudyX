package com.example.betterStudy.model;

import com.example.betterStudy.model.enums.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

import static com.example.betterStudy.model.enums.UserRole.TEACHER;
@Table(name = "users")
public class User implements UserDetails {
    private String email;
    private String password;
    @Enumerated(value = EnumType.STRING)
    private UserRole userRole; // teacher, student

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    SimpleGrantedAuthority simpleGrantedAuthority;
    simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_"+userRole.name());
    List<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(simpleGrantedAuthority);
    return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
