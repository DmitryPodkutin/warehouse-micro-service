package com.gmail.podkutin.dmitry.security;

import com.gmail.podkutin.dmitry.exeption.NotFoundException;
import com.gmail.podkutin.dmitry.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    public UserDetailServiceImpl(@Autowired UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return SecurityUser.fromUser(repository.findByEmail(username)
                .orElseThrow(() -> new NotFoundException("User by email " + username + " doesn't exist")));
    }
}
