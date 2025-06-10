package com.example.hrmdemo.dto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "departments")
public class Department extends BaseEntity {

    private String name;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Facility facility;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;
}
