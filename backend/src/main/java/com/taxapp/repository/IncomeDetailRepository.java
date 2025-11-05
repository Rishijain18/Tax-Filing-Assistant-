package com.taxapp.repository;

import com.taxapp.model.entities.IncomeDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeDetailRepository extends JpaRepository<IncomeDetail, Long> {
}
