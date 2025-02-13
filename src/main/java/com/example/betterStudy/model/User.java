package com.example.betterStudy.model;

import com.example.betterStudy.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

import static com.example.betterStudy.model.enums.UserRole.TEACHER;
@Table(name = "users")
@Entity // :D xd
@NoArgsConstructor // hibernate potrzebuje tego
@EqualsAndHashCode(of = "id") // warto dodac tutaj, jakby te user bylo jako kolekcja innej encji to by moglo ci nie
// dodac np usera do niej

public class User implements UserDetails {
    //tera git?
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String password;
    @Enumerated(value = EnumType.STRING)
    private UserRole userRole; // teacher, student

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority;
        simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_"+userRole.name());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(simpleGrantedAuthority); // formatujemy! :D
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
