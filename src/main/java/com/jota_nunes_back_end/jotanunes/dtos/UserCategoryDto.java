package com.jota_nunes_back_end.jotanunes.dtos;

import com.jota_nunes_back_end.jotanunes.enums.RoleUser;
import com.jota_nunes_back_end.jotanunes.models.UserAccount;
import lombok.Data;

@Data
public class UserCategoryDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer categoriaId;
    private String email;
    private String numberRegister;
    private String phoneNumber;
    private RoleUser roleUser;
    private String nameCategory;


    public UserCategoryDto(UserAccount user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.categoriaId = user.getCategoria().getIdCategoria();
        this.email = user.getEmail();
        this.numberRegister = user.getNumberRegister();
        this.phoneNumber = user.getPhone();
        this.roleUser = user.getRoleUser();
        this.nameCategory = user.getCategoria().getName();
    }
}

