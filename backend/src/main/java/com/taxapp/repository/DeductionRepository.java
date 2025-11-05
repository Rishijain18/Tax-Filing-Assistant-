package com.taxapp.repository;

import com.taxapp.model.entities.Deduction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeductionRepository extends JpaRepository<Deduction, Long> {
}
