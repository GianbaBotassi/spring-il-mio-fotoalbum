package com.exam.springilmiofotoalbum.security;

import com.exam.springilmiofotoalbum.model.Role;
import com.exam.springilmiofotoalbum.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class DatabaseUserDetails implements UserDetails {

    private final Integer id;
    private final String username;
    private final String password;
    private final Set<GrantedAuthority> authorities = new HashSet<>();

    public DatabaseUserDetails(User user) {
        id = user.getId();
        username = user.getEmail();
        password = user.getPassword();
        for (Role role : user.getRoles()
        ) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
