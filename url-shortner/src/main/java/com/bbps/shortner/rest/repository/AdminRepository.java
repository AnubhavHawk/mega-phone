package com.bbps.shortner.rest.repository;

import com.bbps.shortner.rest.model.AdminConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminConfiguration, Long> {

}
