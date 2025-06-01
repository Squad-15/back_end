package com.jota_nunes_back_end.jotanunes.dtos;

import com.jota_nunes_back_end.jotanunes.enums.*;

import java.time.LocalDate;

public class UserAccountDto {
    public String firstName;
    public String lastName;
    public String email;
    public String phone;
    public String urlPicture;
    public RoleUser roleUser;

    public ProfileName cargo;
    public Departament departamento;
    public TypeConnection typeconnection;
    public LocalDate dataAdmissao;
    public Location location;
}
