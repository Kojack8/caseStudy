package com.userdetails;

import com.entitymodels.RoleEntity;
import com.entitymodels.UserEntity;
import com.logging.LombokLoggingController;
import com.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService {



    @Autowired
    private UserRepository userRepository;
    private Logger logger = LoggerFactory.getLogger(LombokLoggingController.class);

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.warn("You're logging in brother!");
        UserEntity user = userRepository.findByEmail(username);
        if (user == null) throw new UsernameNotFoundException("Username not found");

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (RoleEntity role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new CustomUserDetails(user);
    }
}
