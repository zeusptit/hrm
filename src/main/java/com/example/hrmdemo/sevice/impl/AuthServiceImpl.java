package com.example.hrmdemo.sevice.impl;

import com.example.hrmdemo.dto.entity.User;
import com.example.hrmdemo.security.custom.CustomUserDetailsService;
import com.example.hrmdemo.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class AuthServiceImpl implements com.example.hrmdemo.sevice.impl.AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public String login(AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(authentication);
    }

    @Transactional
    @Override
    public String register(RegisterRequest registerRequest) {
        if (userService.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists!");
        }

        User newUser = new User();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        newUser.setEmail(registerRequest.getEmail());
        newUser.setRoles(List.of(Role.USER));

        userService.saveUser(newUser);

        return "User registered successfully!";
    }

    @Override
    public String forgotPassword(ForgotPasswordRequest request) {
        if (userService.findByEmail(request.getEmail()).isEmpty()) {
            throw new IllegalArgumentException("Email not found!");
        }

        String otp = generateOTP();

        try {
            String subject = "OTP for Password Reset";
            String body = "<p>Your OTP for resetting your password is: <b>" + otp + "</b></p>";
            emailService.sendEmail(request.getEmail(), subject, body);
        } catch (MessagingException e) {
            throw new RuntimeException("Error while sending email: " + e.getMessage());
        }

        return otp;
    }

    private String generateOTP() {
        Random rand = new Random();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            otp.append(rand.nextInt(10));
        }
        return otp.toString();
    }

    @Override
    public UserResponse getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            throw new SecurityException("User not authenticated!");
        }

        return getUserResponse((CustomUserDetails) authentication.getPrincipal());
    }

    @Override
    public String resetPassword(ResetPasswordRequest request) {
        Optional<User> userOptional = userService.findByEmail(request.getEmail());
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("Email not found!");
        }

        User user = userOptional.get();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userService.saveUser(user);
        return "Successfully";
    }

    @Override
    public String changePassword(ChangePasswordRequest request) {
        Optional<User> userOptional = userService.findByEmail(request.getEmail());
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("Email not found!");
        }

        User user = userOptional.get();
        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Old password is incorrect!");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userService.saveUser(user);
        return "Successfully";
    }

    private UserResponse getUserResponse(CustomUserDetails principal) {
        return userService.findByEmail(principal.getEmail())
                .map(UserResponse::fromEntity)
                .orElseGet(UserResponse::new);
    }

}

