package com.bbps.scheduler.model;

import javax.persistence.*;

@Entity
@Table(name = "contact_based_field")
public class ContactBasedField {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String billerId;
    private int contactFieldIndex;
    private String mobile_or_email = "mobile";

    public ContactBasedField() {}
    public ContactBasedField(String billerId, int contactFieldIndex, String mobile_or_email) {
        this.billerId = billerId;
        this.contactFieldIndex = contactFieldIndex;
        this.mobile_or_email = mobile_or_email;
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

    public int getContactFieldIndex() {
        return contactFieldIndex;
    }

    public void setContactFieldIndex(int timeFieldIndex) {
        this.contactFieldIndex = timeFieldIndex;
    }

    public String getMobileOrEmail() {
        return mobile_or_email;
    }

    public void setMobileOrEmail(String mobile_or_email) {
        this.mobile_or_email = mobile_or_email;
    }
}
