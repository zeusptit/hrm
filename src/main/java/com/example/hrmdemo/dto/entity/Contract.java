package com.example.hrmdemo.dto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "contracts")
@Getter
@Setter
public class Contract extends BaseEntity{

    private String type;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal salaryBase;
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}