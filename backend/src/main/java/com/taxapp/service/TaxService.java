package com.taxapp.service;

import com.taxapp.model.TaxCalculationRequest;
import com.taxapp.model.TaxCalculationResponse;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class TaxService {

    public TaxCalculationResponse calculateTax(TaxCalculationRequest req) {
        double totalIncome = 0.0;
        if (req.getSalary() != null) totalIncome += req.getSalary();
        if (req.getBusinessIncome() != null) totalIncome += req.getBusinessIncome();
        if (req.getOtherIncome() != null) totalIncome += req.getOtherIncome();
        double totalDeductions = 0.0;
        if (req.getDeductions() != null) {
            totalDeductions = req.getDeductions().values().stream().mapToDouble(Double::doubleValue).sum();
        }
        double taxableIncome = Math.max(0, totalIncome - totalDeductions);

        // Simple progressive slab example (for demonstration) — adjust for real rules
        double tax = 0.0;
        double remaining = taxableIncome;

        if (remaining <= 250000) {
            tax = 0;
        } else {
            if (remaining <= 500000) {
                tax += (remaining - 250000) * 0.05;
            } else {
                tax += 250000 * 0.05; // 250k-500k
                if (remaining <= 1000000) {
                    tax += (remaining - 500000) * 0.2;
                } else {
                    tax += 500000 * 0.2; // 500k-1M
                    tax += (remaining - 1000000) * 0.3;
                }
            }
        }

        TaxCalculationResponse resp = new TaxCalculationResponse();
        resp.setTotalIncome(totalIncome);
        resp.setDeductionsTotal(totalDeductions);
        resp.setTaxableIncome(taxableIncome);
    // round to 2 decimals
    resp.setTaxPayable(Math.round(tax * 100.0) / 100.0);
        resp.setAppliedSlabs("Demo slabs: 0-2.5L:0%, 2.5-5L:5%, 5-10L:20%, >10L:30%");

        return resp;
    }

    public Map<String, Object> suggestDeductions(Map<String, Object> payload) {
        Map<String, Object> result = new HashMap<>();
        double salary = payload.getOrDefault("salary", 0) instanceof Number ? ((Number) payload.get("salary")).doubleValue() : 0.0;
        if (salary > 200000) {
            result.put("80C", "Consider investing up to ₹1.5L in 80C instruments (PF, ELSS, PPF)");
            result.put("80D", "Consider health insurance premium under 80D");
        } else {
            result.put("80C", "80C available if you have eligible investments");
        }
        return result;
    }

    public LocalDate filingDeadline() {
        return LocalDate.of(LocalDate.now().getYear(), 7, 31); // example: 31st July
    }

    public byte[] generatePdf(TaxCalculationResponse resp) throws IOException {
        PDDocument doc = new PDDocument();
        PDPage page = new PDPage();
        doc.addPage(page);

        PDPageContentStream cs = new PDPageContentStream(doc, page);
        cs.beginText();
        cs.setFont(PDType1Font.HELVETICA_BOLD, 14);
        cs.newLineAtOffset(50, 700);
        cs.showText("Tax Summary Report");
        cs.newLineAtOffset(0, -20);
        cs.setFont(PDType1Font.HELVETICA, 12);
        cs.showText("Total Income: " + resp.getTotalIncome());
        cs.newLineAtOffset(0, -15);
        cs.showText("Deductions Total: " + resp.getDeductionsTotal());
        cs.newLineAtOffset(0, -15);
        cs.showText("Taxable Income: " + resp.getTaxableIncome());
        cs.newLineAtOffset(0, -15);
        cs.showText("Tax Payable: " + resp.getTaxPayable());
        cs.endText();
        cs.close();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        doc.save(baos);
        doc.close();
        return baos.toByteArray();
    }
}
