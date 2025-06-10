package com.example.hrmdemo.dto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "facilities")
@Getter
@Setter
public class Facility extends BaseEntity {
    private String name;
    private String address;

    @OneToMany(mappedBy = "facility")
    private List<Department> departments;

    @OneToMany(mappedBy = "facility")
    private List<Employee> employees;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
