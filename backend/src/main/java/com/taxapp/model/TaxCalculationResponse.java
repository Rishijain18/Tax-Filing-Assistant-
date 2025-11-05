package com.taxapp.model;

public class TaxCalculationResponse {
    private double totalIncome;
    private double deductionsTotal;
    private double taxableIncome;
    private double taxPayable;
    private String appliedSlabs;

    public double getTotalIncome() { return totalIncome; }
    public void setTotalIncome(double totalIncome) { this.totalIncome = totalIncome; }
    public double getDeductionsTotal() { return deductionsTotal; }
    public void setDeductionsTotal(double deductionsTotal) { this.deductionsTotal = deductionsTotal; }
    public double getTaxableIncome() { return taxableIncome; }
    public void setTaxableIncome(double taxableIncome) { this.taxableIncome = taxableIncome; }
    public double getTaxPayable() { return taxPayable; }
    public void setTaxPayable(double taxPayable) { this.taxPayable = taxPayable; }
    public String getAppliedSlabs() { return appliedSlabs; }
    public void setAppliedSlabs(String appliedSlabs) { this.appliedSlabs = appliedSlabs; }
}
