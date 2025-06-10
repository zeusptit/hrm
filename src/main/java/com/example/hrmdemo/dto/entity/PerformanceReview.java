package com.example.hrmdemo.dto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "performance_reviews")
@Getter
@Setter
public class PerformanceReview extends BaseEntity {
    private LocalDate reviewDate;
    private String reviewer;
    private int score;
    private String comments;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}