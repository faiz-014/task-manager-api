package com.Faiz.tasks.services;

import com.Faiz.tasks.dtos.requestDtos.LoginRequest;
import com.Faiz.tasks.dtos.requestDtos.UserRequest;
import com.Faiz.tasks.exceptions.UserAlreadyExistsException;
import com.Faiz.tasks.models.User;
import com.Faiz.tasks.repositories.UserRepository;
import com.Faiz.tasks.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public String register(UserRequest userRequest) {
        if(userRepository.existsByEmail(userRequest.email())) {
            throw new UserAlreadyExistsException(userRequest.email());
        }
        User user = User.builder()
                        .name(userRequest.name())
                                .email(userRequest.email())
                                        .password(passwordEncoder.encode(userRequest.password()))
                                                .build();
        userRepository.save(user);
        return "User registerd, "+user.getEmail();
    }

    public String login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.email(),
                        loginRequest.password()
                )
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(userDetails.getUsername());
        return token;
    }
}
