package pinomaker.mbti.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pinomaker.mbti.domain.User;
import pinomaker.mbti.repository.UserJpaRepository;

import java.util.*;
import java.util.stream.Collectors;

@Component("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserJpaRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return userRepository.findUserById(username).map(this::createUserDetails).orElseThrow(() -> new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    private UserDetails createUserDetails(User user) {
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getId())
                .password(user.getPassword())
                .authorities(String.valueOf(user.getAuthority()))
                .build();
    }
}
