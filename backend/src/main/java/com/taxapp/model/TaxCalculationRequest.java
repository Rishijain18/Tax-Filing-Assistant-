package com.taxapp.model;

import java.util.Map;

public class TaxCalculationRequest {
    private Double salary;
    private Double businessIncome;
    private Double otherIncome;
    private Map<String, Double> deductions;

    public Double getSalary() { return salary; }
    public void setSalary(Double salary) { this.salary = salary; }
    public Double getBusinessIncome() { return businessIncome; }
    public void setBusinessIncome(Double businessIncome) { this.businessIncome = businessIncome; }
    public Double getOtherIncome() { return otherIncome; }
    public void setOtherIncome(Double otherIncome) { this.otherIncome = otherIncome; }
    public Map<String, Double> getDeductions() { return deductions; }
    public void setDeductions(Map<String, Double> deductions) { this.deductions = deductions; }
}
