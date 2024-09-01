package com.project.authorization_server.service;

import com.project.authorization_server.config.PasswordUtil;
import com.project.authorization_server.entity.MemberVo;
import com.project.authorization_server.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("userDetailService")
public class AuthUserService implements UserDetailsService {

    private final MemberRepository repository;
    private final PasswordUtil passwordUtil;

    public AuthUserService(MemberRepository repository, PasswordUtil passwordUtil) {
        this.repository = repository;
        this.passwordUtil = passwordUtil;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("UserName: {}", username);

        // find user
        MemberVo memberVo = repository.findUserByUserId(username);
        if (memberVo == null) {
            log.info("User Not Found: {}", username);
            return null;
        }

        UserDetails userDetails = User.builder()
                .username(memberVo.getUserId())
                .password(memberVo.getUserPw())
                .roles("USER")
                .build();

        return userDetails;
    }

    public List<GrantedAuthority> getGrantedAuthorities(String role) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        switch(role) {
            case "USER":
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+"USER"));
            case "ADMIN":
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+"ADMIN"));
            default:
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+"USER"));
        }
        return grantedAuthorities;
    }
}
