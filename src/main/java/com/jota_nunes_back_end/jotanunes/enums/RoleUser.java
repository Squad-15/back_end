package com.jota_nunes_back_end.jotanunes.enums;

public enum RoleUser {
    GESTOR("GESTOR"),
    COLABORADOR("COLABORADOR"),
    ADMINISTRADOR("ADMINISTRADOR");

    private final String role;

    RoleUser(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}