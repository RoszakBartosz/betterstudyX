package com.example.betterStudy.model;

import com.example.betterStudy.model.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

import static com.example.betterStudy.model.enums.UserRole.TEACHER;
@Data
@Table(name = "users")
@Entity
@AllArgsConstructor
@NoArgsConstructor // hibernate potrzebuje tego
@EqualsAndHashCode(of = "id") // warto dodac tutaj, jakby te user bylo jako kolekcja innej encji to by moglo ci nie
// dodac np usera do niej
@Builder
public class User implements UserDetails {
    //tera git?
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Email
    private String email;
    @NotBlank(message = "The password cannot be empty ")
    private String password;
    @Enumerated(value = EnumType.STRING)
    @NotNull(message = "UserRole cannot be null ")
    private UserRole userRole; // teacher, student

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority;
        simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_" +userRole.name());
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
