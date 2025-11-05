package com.taxapp.repository;

import com.taxapp.model.entities.TaxSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxSummaryRepository extends JpaRepository<TaxSummary, Long> {
}
