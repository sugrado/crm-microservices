package com.turkcell.crm.customer_service.entities.concretes;

import com.turkcell.crm.customer_service.core.entities.BaseEntity;
import com.turkcell.crm.customer_service.entities.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "individual_customers")
@SQLRestriction(value = "deleted_date is null")
public class IndividualCustomer extends BaseEntity<Integer> {

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "middle_name", length = 50)
    private String middleName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "home_phone", length = 11)
    private String homePhone;

    @Column(name = "nationality_id", nullable = false, unique = true, updatable = false, length = 11)
    private String nationalityId;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "mother_name", length = 50)
    private String motherName;

    @Column(name = "father_name", length = 50)
    private String fatherName;

    @Column(name = "gender", nullable = false)
    private Gender gender;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
