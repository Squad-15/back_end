package com.jota_nunes_back_end.jotanunes.dtos;

import com.jota_nunes_back_end.jotanunes.models.Modulo;
import lombok.Data;

@Data
public class ModuloDto {
    private int id;
    private String name;
    private String description;

    public ModuloDto(Modulo modulo) {
        this.id = modulo.getId();
        this.name = modulo.getName();
        this.description = modulo.getDescription();
    }
}
