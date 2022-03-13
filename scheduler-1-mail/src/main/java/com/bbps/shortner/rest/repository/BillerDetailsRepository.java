package com.bbps.shortner.rest.repository;

import com.bbps.shortner.rest.model.BillerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillerDetailsRepository extends JpaRepository<BillerDetails, Integer> {
}
