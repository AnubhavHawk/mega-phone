package com.bbps.scheduler.repository;

import com.bbps.scheduler.model.BillerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillerDetailsRepository extends JpaRepository<BillerDetails, Integer> {
}
