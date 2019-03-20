package br.com.vfs.marketplacebackend.service;

import br.com.vfs.marketplacebackend.config.jwt.JwtProvider;
import br.com.vfs.marketplacebackend.entity.RoleEntity;
import br.com.vfs.marketplacebackend.entity.RoleEntity.RoleName;
import br.com.vfs.marketplacebackend.entity.UserEntity;
import br.com.vfs.marketplacebackend.message.request.LoginRequest;
import br.com.vfs.marketplacebackend.message.request.SignUpRequest;
import br.com.vfs.marketplacebackend.message.response.JwtResponse;
import br.com.vfs.marketplacebackend.message.response.ResponseMessage;
import br.com.vfs.marketplacebackend.repository.RoleRepository;
import br.com.vfs.marketplacebackend.repository.UserRepository;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           AuthenticationManager authenticationManager, JwtProvider jwtProvider,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }

    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        //adicionando autenticacao ao contexto
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //gerando token
        String jwt = jwtProvider.generateJwtToken(authentication);
        //obtendo UserDetails
        return JwtResponse.builder()
                .token(jwt)
                .build();
    }

    public ResponseEntity<?> registerUser(SignUpRequest signUpRequest) {

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(ResponseMessage.builder().message("Fail -> Username is already taken!").build(),
                    HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(ResponseMessage.builder().message("Fail -> Email is already in use!").build(),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        UserEntity user = UserEntity.builder()
                .name(signUpRequest.getName())
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .build();

        Set<String> strRoles = signUpRequest.getRole();
        Set<RoleEntity> roles = new HashSet<>();
        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    RoleEntity adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(adminRole);
                    break;
                case "pm":
                    RoleEntity pmRole = roleRepository.findByName(RoleName.ROLE_PM)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(pmRole);

                    break;
                default:
                    RoleEntity userRole = roleRepository.findByName(RoleName.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(userRole);
            }
        });
        user.setRoles(roles);
        userRepository.save(user);
        return new ResponseEntity<>(ResponseMessage.builder().message("User registered successfully!").build(), HttpStatus.OK);
    }
}
