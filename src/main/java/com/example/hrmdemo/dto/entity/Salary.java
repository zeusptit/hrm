package com.example.hrmdemo.dto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "salaries")
@Getter
@Setter
public class Salary extends BaseEntity {
    private int month;
    private int year;
    private BigDecimal baseSalary;
    private BigDecimal bonus;
    private BigDecimal deduction;
    private BigDecimal totalSalary;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}