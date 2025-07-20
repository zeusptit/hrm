package com.example.hrmdemo.dto.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employees")
@Getter
@Setter
public class Employee extends BaseEntity{

    private String fullName;
    private String email;
    private String phone;
    private String gender;
    private LocalDate dob;
    private String address;
    private LocalDate hireDate;
    private String status;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Facility facility;

    @OneToMany(mappedBy = "employee")
    private List<Contract> contracts;

    @OneToMany(mappedBy = "employee")
    private List<Attendance> attendances;

    @OneToMany(mappedBy = "employee")
    private List<Salary> salaries;

    @OneToMany(mappedBy = "employee")
    private List<PerformanceReview> performanceReviews;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private User user;
}