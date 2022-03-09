package com.bbps.shortner.rest.model;

import javax.persistence.Entity;
import java.time.LocalDateTime;

public class CreationResponse {
    private String originalUrl;
    private String shortUrl;
    private String shortCode;
    private LocalDateTime createAt;
    private LocalDateTime validTill;
    private String creator;

    public CreationResponse(String originalUrl, String shortUrl, String shortCode, LocalDateTime createAt, LocalDateTime validTill, String creator) {
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
        this.shortCode = shortCode;
        this.createAt = createAt;
        this.validTill = validTill;
        this.creator = creator;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getValidTill() {
        return validTill;
    }

    public void setValidTill(LocalDateTime validTill) {
        this.validTill = validTill;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
