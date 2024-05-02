package com.turkcell.crm.common.kafka.events;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CustomerCreatedEvent {
    private int id;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private LocalDateTime deletedDate;
    private String firstName;
    private String middleName;
    private String lastName;
    private String homePhone;
    private String nationalityId;
    private LocalDate birthDate;
    private String motherName;
    private String fatherName;
    private String gender;
    private String email;
    private String mobilePhone;

    public CustomerCreatedEvent(int id, LocalDateTime createdDate, LocalDateTime updatedDate, LocalDateTime deletedDate, String firstName, String middleName, String lastName, String homePhone, String nationalityId, LocalDate birthDate, String motherName, String fatherName, String gender, String email, String mobilePhone) {
        this.id = id;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.deletedDate = deletedDate;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.homePhone = homePhone;
        this.nationalityId = nationalityId;
        this.birthDate = birthDate;
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.gender = gender;
        this.email = email;
        this.mobilePhone = mobilePhone;
    }

    public CustomerCreatedEvent() {
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public LocalDateTime getDeletedDate() {
        return deletedDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getNationalityId() {
        return nationalityId;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getMotherName() {
        return motherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public void setDeletedDate(LocalDateTime deletedDate) {
        this.deletedDate = deletedDate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public void setNationalityId(String nationalityId) {
        this.nationalityId = nationalityId;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
}
