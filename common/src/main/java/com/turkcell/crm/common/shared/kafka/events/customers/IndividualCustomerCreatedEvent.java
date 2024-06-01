package com.turkcell.crm.common.shared.kafka.events.customers;

public class IndividualCustomerCreatedEvent {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String nationalityId;
    private String mobilePhone;

    public IndividualCustomerCreatedEvent(int id, String firstName, String middleName, String lastName, String nationalityId, String mobilePhone) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nationalityId = nationalityId;
        this.mobilePhone = mobilePhone;
    }

    public IndividualCustomerCreatedEvent() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(String nationalityId) {
        this.nationalityId = nationalityId;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
}
