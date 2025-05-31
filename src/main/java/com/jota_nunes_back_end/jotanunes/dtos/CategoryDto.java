package com.jota_nunes_back_end.jotanunes.dtos;

import com.jota_nunes_back_end.jotanunes.models.Categorias;
import lombok.Data;

@Data
public class CategoryDto {
    private int id;
    private String name;

    public CategoryDto(Categorias categorias) {
        this.id = categorias.getIdCategoria();
        this.name = categorias.getName();
    }
}
