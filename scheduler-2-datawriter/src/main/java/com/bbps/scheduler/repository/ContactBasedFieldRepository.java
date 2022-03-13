package com.bbps.scheduler.repository;

import com.bbps.scheduler.model.ContactBasedField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactBasedFieldRepository extends JpaRepository<ContactBasedField, Integer> {
    ContactBasedField findByBillerId(String billerId);
}