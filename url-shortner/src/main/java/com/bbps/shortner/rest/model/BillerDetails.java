package com.bbps.shortner.rest.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "biller_details")
public class BillerDetails {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private int id;
    private String billerId;
    private String billerEmail;
    private String billerMessage;
    private String custParams;
    private String dueDate;
    private String nextTriggerDate;
    private String timeFormat;
    private boolean isActive = true;
    private String customerMobile;
    private Date dueDateTimestamp;

    public Date getDueDateTimestamp() {
        return dueDateTimestamp;
    }

    public void setDueDateTimestamp(Date dueDateTimestamp) {
        this.dueDateTimestamp = dueDateTimestamp;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public BillerDetails() {
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

    public String getBillerEmail() {
        return billerEmail;
    }

    public void setBillerEmail(String billerEmail) {
        this.billerEmail = billerEmail;
    }

    public String getBillerMessage() {
        return billerMessage;
    }

    public void setBillerMessage(String billerMessage) {
        this.billerMessage = billerMessage;
    }

    public String getCustParams() {
        return custParams;
    }

    public void setCustParams(String custParams) {
        this.custParams = custParams;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getNextTriggerDate() {
        return nextTriggerDate;
    }

    public void setNextTriggerDate(String nextTriggerDate) {
        this.nextTriggerDate = nextTriggerDate;
    }

    public String getTimeFormat() {
        return timeFormat;
    }

    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
