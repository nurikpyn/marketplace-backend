package br.com.vfs.marketplacebackend.service;

import br.com.vfs.marketplacebackend.dto.UserPrinciple;
import br.com.vfs.marketplacebackend.entity.UserEntity;
import br.com.vfs.marketplacebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format("Usuário não encontrado. Nickname ou email: %s", username))
        );
        return UserPrinciple.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .authorities(user.getRoles().stream().map(role ->
                        new SimpleGrantedAuthority(role.getName().name())
                ).collect(Collectors.toList()))
                .build();
    }
}
