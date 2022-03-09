package com.bbps.shortner.rest.model;

import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import java.time.LocalDateTime;

public class ShortUrlCreationRequest {
    private String originalUrl;
    private String billerId; // Creator of the URL.
    @Nullable
    private LocalDateTime validity;

    public LocalDateTime getValidity() {
        return validity;
    }

    public void setValidity(LocalDateTime validity) {
        this.validity = validity;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public void setBillerId(String billerId) {
        this.billerId = billerId;
    }

    public ShortUrlCreationRequest(String originalUrl, String billerId) {
        this.originalUrl = originalUrl;
        this.billerId = billerId;
    }

    public ShortUrlCreationRequest() {
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getBillerId() {
        return billerId;
    }

}
