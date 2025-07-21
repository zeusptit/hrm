package com.example.hrmdemo.sevice;

import com.example.hrmdemo.dto.request.AuthRequest;
import com.example.hrmdemo.dto.request.ChangePasswordRequest;
import com.example.hrmdemo.dto.request.ForgotPasswordRequest;
import com.example.hrmdemo.dto.request.RegisterRequest;
import com.example.hrmdemo.dto.request.ResetPasswordRequest;
import com.example.hrmdemo.dto.response.UserResponse;
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
