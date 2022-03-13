package com.bbps.shortner.rest.repository;

import com.bbps.shortner.rest.model.BillerFieldMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillerFieldMappingRepository extends JpaRepository<BillerFieldMapping, Integer> {
    BillerFieldMapping findByBillerId(String billerId);
}
