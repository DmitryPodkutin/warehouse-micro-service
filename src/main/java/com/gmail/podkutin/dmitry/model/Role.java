package com.gmail.podkutin.dmitry.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(Set.of(Permission.READ)),
    ADMIN(Set.of(Permission.READ, Permission.WRITE));

    private final Set<Permission> permission;

    Role(Set<Permission> permissions) {
        this.permission = permissions;
    }

    public Set<Permission> getPermission() {
        return permission;
    }

    public Set<SimpleGrantedAuthority> getAuthority() {
        return getPermission().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
