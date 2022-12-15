package com.druiz.fullstack.wikirap.utils.config.jwt;

import com.druiz.fullstack.wikirap.user.domain.Role;
import com.druiz.fullstack.wikirap.user.domain.UserEnt;
import com.druiz.fullstack.wikirap.user.infrastructure.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceCustom  implements UserDetailsService {

    private UserRepo userRepository;

    @Autowired
    public UserDetailsServiceCustom(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEnt userEnt = userRepository.findByUsername(username);
        return new User(userEnt.getUsername(), userEnt.getPassword(), mapRolesToAuthorities(userEnt.getRoles()));
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
