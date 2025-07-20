package com.example.hrmdemo.sevice.impl;

import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    String login(AuthRequest authRequest);
    String register(RegisterRequest authRequest);
    String forgotPassword(ForgotPasswordRequest request);
    UserResponse getCurrentUser();

    String resetPassword(ResetPasswordRequest request);

    String changePassword(ChangePasswordRequest request);
}
