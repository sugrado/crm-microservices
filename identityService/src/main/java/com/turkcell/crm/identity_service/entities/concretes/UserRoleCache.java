package com.turkcell.crm.identity_service.entities.concretes;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserRoleCache implements Serializable {
    private String username;
    private List<String> roles;

    public UserRoleCache(String username) {
        this.username = username;
        this.roles = new ArrayList<>();
    }
}