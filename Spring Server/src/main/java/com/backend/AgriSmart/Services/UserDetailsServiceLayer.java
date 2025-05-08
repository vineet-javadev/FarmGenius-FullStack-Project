package com.backend.AgriSmart.Services;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.backend.AgriSmart.Entities.UserEntity;
import com.backend.AgriSmart.Repositories.UserRepository;

@Service
public class UserDetailsServiceLayer implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final UserEntity user;
        if (username.endsWith(".com")) {
            user = userRepository.findByUserEmail(username).get();
        } else {
            user = userRepository.findByUserId(username).get();
        }

        if (user == null) {
            throw new UsernameNotFoundException("User not found { UserDetailsServiceLayer service }");
        }
        return new UserDetails() {

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return user.getUserRole().stream()
                        .map(role -> new SimpleGrantedAuthority(role.startsWith("ROLE_") ? role : "ROLE_" + role))
                        .collect(Collectors.toList());
            }

            @Override
            public String getUsername() {
                return user.getUserId();
            }

            @Override
            public String getPassword() {
                return user.getUserPassword();
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
        };
    }

}
