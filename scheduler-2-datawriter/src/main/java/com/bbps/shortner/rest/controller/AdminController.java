package com.bbps.shortner.rest.controller;

import com.bbps.shortner.rest.model.AdminConfiguration;
import com.bbps.shortner.rest.service.AdminConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
public class AdminController {
    @Autowired
    private AdminConfigurationService adminConfigurationService;

    @PostMapping("/admin/change-configuration")
    public ResponseEntity<?> chnageConfiguration(@RequestBody Map<Object, Object> requestBody) {
        AdminConfiguration configuration = adminConfigurationService.getAdminConfiguration();
        if(requestBody.containsKey("defaultValidity")) {
            Long validity = new Long((int) requestBody.get("defaultValidity"));
            configuration.setDefaultValidity(validity);
        }
        if(requestBody.containsKey("domain")) {
            String domain = (String) requestBody.get("domain");
            configuration.setDefaultDomain(domain);
        }
        if(requestBody.containsKey("maxValidity")) {
            Long maxValidity = new Long((int) requestBody.get("maxValidity"));
            configuration.setMaxValidity(maxValidity);
        }
        return ResponseEntity.ok(adminConfigurationService.update(configuration));
    }
    @GetMapping("/admin/get-configuration")
    public ResponseEntity<?> getConfiguration() {
        return ResponseEntity.ok(adminConfigurationService.getAdminConfiguration());
    }
}
