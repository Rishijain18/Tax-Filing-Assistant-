package com.taxapp.model.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tax_summary")
public class TaxSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Double totalIncome;
    private Double deductionsTotal;
    private Double taxPayable;
    private LocalDateTime createdAt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Double getTotalIncome() { return totalIncome; }
    public void setTotalIncome(Double totalIncome) { this.totalIncome = totalIncome; }
    public Double getDeductionsTotal() { return deductionsTotal; }
    public void setDeductionsTotal(Double deductionsTotal) { this.deductionsTotal = deductionsTotal; }
    public Double getTaxPayable() { return taxPayable; }
    public void setTaxPayable(Double taxPayable) { this.taxPayable = taxPayable; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
