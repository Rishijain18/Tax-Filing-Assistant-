package com.taxapp.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "income_details")
public class IncomeDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String incomeSource;
    private Double amount;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getIncomeSource() { return incomeSource; }
    public void setIncomeSource(String incomeSource) { this.incomeSource = incomeSource; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
}
