package com.jota_nunes_back_end.jotanunes.dtos;

import com.jota_nunes_back_end.jotanunes.models.CategoriaTrilha;
import lombok.Data;

@Data
public class CategoriaTrilhaDto {
    private Long id;
    private Long trilhaId;
    private Long categoriaId;
    private String nameCategory;
    private String nameTrilha;
    private String descricaoTrilha;

    public CategoriaTrilhaDto(CategoriaTrilha categoriaTrilha) {
        this.id = Long.valueOf(categoriaTrilha.getId());
        this.trilhaId = Long.valueOf(categoriaTrilha.getTrilha().getId());
        this.categoriaId = Long.valueOf(categoriaTrilha.getCategoria().getIdCategoria());
        this.nameTrilha = categoriaTrilha.getTrilha().getName();
        this.descricaoTrilha = categoriaTrilha.getTrilha().getDescription();
        this.nameCategory = categoriaTrilha.getCategoria().getName();
    }
}
