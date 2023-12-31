package com.bkikenski.ultimatefitness.model.enumerations;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ROLE_COSTUMER,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
