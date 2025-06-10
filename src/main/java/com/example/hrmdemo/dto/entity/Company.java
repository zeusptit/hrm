package com.example.hrmdemo.dto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "companies")
@Getter
@Setter
public class Company extends BaseEntity {
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String website;
    private String taxId;
    private String registrationNumber;
    private String description;

    @OneToMany
    private List<Facility> facilities;
}
