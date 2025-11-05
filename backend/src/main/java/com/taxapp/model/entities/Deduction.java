package com.taxapp.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "deductions")
public class Deduction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String section;
    private Double amount;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getSection() { return section; }
    public void setSection(String section) { this.section = section; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
}
