package com.bbps.shortner.rest.model;

import javax.persistence.*;

@Entity
@Table(name = "time_based_field")
public class TimeBasedField {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String billerId;
    private int timeFieldIndex;
    private String timeFormat;

    public TimeBasedField() {}
    public TimeBasedField(String billerId, int timeFieldIndex, String timeFormat) {
        this.billerId = billerId;
        this.timeFieldIndex = timeFieldIndex;
        this.timeFormat = timeFormat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBillerId() {
        return billerId;
    }

    public void setBillerId(String billerId) {
        this.billerId = billerId;
    }

    public int getTimeFieldIndex() {
        return timeFieldIndex;
    }

    public void setTimeFieldIndex(int timeFieldIndex) {
        this.timeFieldIndex = timeFieldIndex;
    }

    public String getTimeFormat() {
        return timeFormat;
    }

    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }
}
