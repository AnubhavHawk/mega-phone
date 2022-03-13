package com.bbps.shortner.rest.service;

import com.bbps.shortner.rest.model.AdminConfiguration;
import com.bbps.shortner.rest.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminConfigurationService {
    @Autowired
    private AdminRepository adminRepository;

    public AdminConfiguration getAdminConfiguration() {
        return adminRepository.findAll().get(0);
    }
    public AdminConfiguration update(AdminConfiguration adminConfiguration) {
        return adminRepository.save(adminConfiguration);
    }
}
