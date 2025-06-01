package com.jota_nunes_back_end.jotanunes.dtos;

import com.jota_nunes_back_end.jotanunes.enums.RoleUser;
import com.jota_nunes_back_end.jotanunes.models.CategoriaUsuario;
import lombok.Data;

@Data
public class UserCategoryDto {
    private int categoryUserId;
    private int userId;
    private int categoryId;
    private String first_name;
    private String last_name;
    private String email;
    private String numberRegister;
    private String phoneNumber;
    private RoleUser roleUser;
    private String nameCategory;

    public UserCategoryDto(CategoriaUsuario categoriaUsuario) {
        this.categoryUserId = categoriaUsuario.getIdCategoria();
        this.userId = Math.toIntExact(categoriaUsuario.getUserAccount().getId());
        this.categoryId = categoriaUsuario.getIdCategoria();
        this.first_name = categoriaUsuario.getUserAccount().getFirstName();
        this.last_name = categoriaUsuario.getUserAccount().getLastName();
        this.email = categoriaUsuario.getUserAccount().getEmail();
        this.numberRegister = categoriaUsuario.getUserAccount().getNumberRegister();
        this.phoneNumber = categoriaUsuario.getUserAccount().getPhone();
        this.roleUser = categoriaUsuario.getUserAccount().getRoleUser();
        this.nameCategory = categoriaUsuario.getCategorias().getName();
    }
}
