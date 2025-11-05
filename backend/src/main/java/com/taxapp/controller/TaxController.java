package com.taxapp.controller;

import com.taxapp.model.TaxCalculationRequest;
import com.taxapp.model.TaxCalculationResponse;
import com.taxapp.service.TaxService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TaxController {

    private final TaxService taxService;

    public TaxController(TaxService taxService) {
        this.taxService = taxService;
    }

    @PostMapping("/calculateTax")
    public TaxCalculationResponse calculateTax(@RequestBody TaxCalculationRequest req) {
        return taxService.calculateTax(req);
    }

    @PostMapping("/deductions")
    public Map<String, Object> suggestions(@RequestBody Map<String, Object> payload) {
        // simple heuristic: suggest 80C if salary > threshold
        return taxService.suggestDeductions(payload);
    }

    @GetMapping("/reminder")
    public Map<String, Object> reminder() {
        LocalDate deadline = taxService.filingDeadline();
        return Map.of("deadline", deadline.toString(), "message", "Remember to file your taxes before the deadline.");
    }

    @PostMapping("/generatePDF")
    public ResponseEntity<byte[]> generatePdf(@RequestBody TaxCalculationResponse resp) throws IOException {
    byte[] pdf = taxService.generatePdf(resp);
    // Null-safety: ensure generated PDF bytes are not null before building the response
    Objects.requireNonNull(pdf, "Generated PDF must not be null");
    // Also guard the MediaType constant for static null-safety checks
    MediaType pdfMediaType = Objects.requireNonNull(MediaType.APPLICATION_PDF, "MediaType.APPLICATION_PDF must not be null");
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=tax-summary.pdf")
        .contentType(pdfMediaType)
        .body(pdf);
    }
}
