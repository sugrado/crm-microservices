package com.turkcell.crm.identity_service.entities.concretes;

import com.turkcell.crm.identity_service.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")
@SQLRestriction(value = "deleted_date is null")
public class Role extends BaseEntity<Integer> implements GrantedAuthority {
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<UserRole> users;

    @Override
    public String getAuthority() {
        return this.name;
    }
}
