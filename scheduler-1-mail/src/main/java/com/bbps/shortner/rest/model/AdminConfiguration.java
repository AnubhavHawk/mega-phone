package com.bbps.shortner.rest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AdminConfiguration {
    @Id
    @GeneratedValue
    private long id;
    private long maxValidity = 45 * 24 * 60 * 60; // 45 days maximum
    private long defaultValidity = 30 * 24 * 60 * 60; // 30 days in seconds
    private String defaultDomain = "me.ga";

    public String getDefaultDomain() {
        return defaultDomain;
    }

    public void setDefaultDomain(String defaultDomain) {
        this.defaultDomain = defaultDomain;
    }

    public AdminConfiguration(long maxValidity, long defaulValidity) {
        this.maxValidity = maxValidity;
        this.defaultValidity = defaulValidity;
    }
    public AdminConfiguration() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        id = id;
    }

    public long getMaxValidity() {
        return maxValidity;
    }

    public void setMaxValidity(long maxValidity) {
        this.maxValidity = maxValidity;
    }

    public long getDefaultValidity() {
        return defaultValidity;
    }

    @Override
    public String toString() {
        return "AdminConfiguration{" +
                "id=" + id +
                ", maxValidity=" + maxValidity +
                ", defaultValidity=" + defaultValidity +
                ", defaultDomain=" + defaultDomain +
                '}';
    }

    public void setDefaultValidity(long defaultValidity) {
        this.defaultValidity = defaultValidity;
    }
}
