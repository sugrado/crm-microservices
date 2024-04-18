package com.turkcell.crm.customer_service.entities.concretes;

import com.turkcell.crm.customer_service.core.entities.BaseEntity;
import com.turkcell.crm.customer_service.entities.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer extends BaseEntity<Integer> {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name ="nationality_id")
    private String nationalityId;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "mother_name")
    private String motherName;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
}