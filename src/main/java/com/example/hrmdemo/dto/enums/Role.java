package com.example.hrmdemo.dto.enums;

import lombok.Getter;

@Getter
public enum Role {
    SYSTEM_ADMIN("ROLE_SYSTEM_ADMIN"),
    HR_MANAGER("ROLE_HR_MANAGER"),
    EMPLOYEE("ROLE_EMPLOYEE"),
    COMPANY_ADMIN("ROLE_COMPANY_ADMIN"),
    FACILITY_ADMIN("ROLE_FACILITY_ADMIN");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
