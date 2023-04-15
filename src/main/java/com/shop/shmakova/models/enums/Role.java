package com.shop.shmakova.models.enums;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author sh1chiro 20.03.2023
 */
public enum Role implements GrantedAuthority {
    ROLE_USER, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
