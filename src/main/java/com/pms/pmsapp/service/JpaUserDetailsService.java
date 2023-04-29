package com.pms.pmsapp.service;

import com.pms.pmsapp.entity.User;
import com.pms.pmsapp.repository.UserRepository;
import com.pms.pmsapp.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
//@AllArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userInfo = userRepository.findByName(username);
        return userInfo.map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

    }
}
