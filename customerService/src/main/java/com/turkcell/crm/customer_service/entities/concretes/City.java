package com.turkcell.crm.customer_service.entities.concretes;

import com.turkcell.crm.customer_service.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cities")
public class City extends BaseEntity<Integer> {

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<Address> addresses;

    public City(int id) {
        setId(id);
    }
}
