package com.example.hrmdemo.security.custom;

import com.example.hrmdemo.dto.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    private Long userId;
    private String email;
    private String password;
    private List<Role> roles;
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public String getUsername() {
        return email;
    }
}
