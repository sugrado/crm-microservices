package com.turkcell.crm.common.shared.dtos.customers;

public class GetIndividualCustomerInvoiceInfoDto {
    private String firstName;
    private String lastName;
    private String email;
    private String mobilePhone;
    private String nationalityId;
    private GetInvoiceInfoByAddressDto address;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(String nationalityId) {
        this.nationalityId = nationalityId;
    }

    public GetInvoiceInfoByAddressDto getAddress() {
        return address;
    }

    public void setAddress(GetInvoiceInfoByAddressDto address) {
        this.address = address;
    }
}

