package com.bbps.shortner.rest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class LongUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long urlId;
    private String originalUrl;
    private String shortUrl;
    private LocalDateTime createAt = LocalDateTime.now();
    private LocalDateTime validTill;
    private String creator;

    public LongUrl(String originalUrl, String shortUrl, LocalDateTime createAt, LocalDateTime validTill, String creator) {
        this.originalUrl = originalUrl;
        this.shortUrl = shortUrl;
        this.createAt = createAt;
        this.validTill = validTill;
        this.creator = creator;
    }

    public LongUrl() {
    }

    @Override
    public String toString() {
        return "LongUrl{" +
                "urlId=" + urlId +
                ", originalUrl='" + originalUrl + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                ", createAt=" + createAt +
                ", validTill=" + validTill +
                ", creator='" + creator + '\'' +
                '}';
    }

    public Long getUrlId() {
        return urlId;
    }

    public void setUrlId(Long urlId) {
        this.urlId = urlId;
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
