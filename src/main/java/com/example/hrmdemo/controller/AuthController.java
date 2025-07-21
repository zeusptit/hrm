package com.example.hrmdemo.controller;

import com.example.hrmdemo.dto.request.*;
import com.example.hrmdemo.dto.response.CommonResponse;
import com.example.hrmdemo.dto.response.UserResponse;
import com.example.hrmdemo.sevice.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

//    @GetMapping("/search")
//    public ResponseEntity<CommonResponse<Page<FamilyResponse>>> searchFamilies(
//            @RequestParam(required = false) String name,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size
//            // select ... limit page offset page * size
//    ) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<FamilyResponse> result = familyService.searchFamiliesByName(name, pageable);
//        return ResponseEntity.ok(new CommonResponse<>(200, "Search with paging success", result));
//    }

    @PostMapping("/login")
    public ResponseEntity<CommonResponse<String>> login(@RequestBody AuthRequest authRequest) {
        try {
            String token = authService.login(authRequest);
            return ResponseEntity.ok(
                    new CommonResponse<>(200, "Login successfully!", token));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new CommonResponse<>(401, "Invalid username or password", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CommonResponse<>(500, e.getMessage(), null));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<CommonResponse<String>> register(@RequestBody RegisterRequest request) {
        try {
            String message = authService.register(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new CommonResponse<>(201, message, null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new CommonResponse<>(400, e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CommonResponse<>(500, e.getMessage(), null));
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<CommonResponse<String>> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        try {
            String otp = authService.forgotPassword(request);
            return ResponseEntity.ok(new CommonResponse<>(200, "Reset password email sent successfully!", otp));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new CommonResponse<>(400, e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CommonResponse<>(500, e.getMessage(), null));
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<CommonResponse<String>> resetPassword(@RequestBody ResetPasswordRequest request) {
        try{
            String message = authService.resetPassword(request);
            return ResponseEntity.ok(new CommonResponse<>(200, "Reset password successfully!", message));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new CommonResponse<>(400, e.getMessage(), null));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CommonResponse<>(500, e.getMessage(), null));
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<CommonResponse<String>> changePassword(@RequestBody ChangePasswordRequest request) {
        try{
            String message = authService.changePassword(request);
            return ResponseEntity.ok(new CommonResponse<>(200, "Reset password successfully!", message));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new CommonResponse<>(400, e.getMessage(), null));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CommonResponse<>(500, e.getMessage(), null));
        }
    }

    @GetMapping("/current-user")
    public ResponseEntity<CommonResponse<UserResponse>> getCurrentUser() {
        try {
            UserResponse user = authService.getCurrentUser();
            return ResponseEntity.ok(new CommonResponse<>(200, "User retrieved successfully!", user));
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new CommonResponse<>(401, e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CommonResponse<>(500, e.getMessage(), null));
        }
    }
}
