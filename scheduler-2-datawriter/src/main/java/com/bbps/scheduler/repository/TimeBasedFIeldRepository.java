package com.bbps.scheduler.repository;

import com.bbps.scheduler.model.TimeBasedField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeBasedFIeldRepository extends JpaRepository<TimeBasedField, Integer> {
    TimeBasedField findByBillerId(String billerId);
}
