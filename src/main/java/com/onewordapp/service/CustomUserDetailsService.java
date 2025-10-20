package com.onewordapp.service;

import com.onewordapp.entity.Author;
import com.onewordapp.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Author author = authorRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return User.withUsername(author.getUsername())
                .password(author.getPassword()) // already encoded in DB
                .roles("USER")
                .build();
    }
}