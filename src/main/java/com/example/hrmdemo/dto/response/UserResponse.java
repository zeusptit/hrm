package com.example.hrmdemo.dto.response;

import com.example.hrmdemo.dto.entity.User;
import com.example.hrmdemo.dto.enums.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
public class UserResponse {
    private Long userId;
    private String username;

    private String email;
    private List<Role> roles;
    private List<Long> familyIds;
    private Long profileId;

    public static UserResponse fromEntity(User entity) {
        UserResponse response = new UserResponse();
        response.setUserId(entity.getId());
        response.setUsername(entity.getUsername());
        response.setEmail(entity.getEmail());
        response.setRoles(entity.getRoles());

        return response;
    }

}
