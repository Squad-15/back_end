package com.jota_nunes_back_end.jotanunes.dtos;

import com.jota_nunes_back_end.jotanunes.models.Trilha;
import lombok.Data;

@Data
public class TrilhaDto {
    private Integer id;
    private String name;
    private String description;

    public TrilhaDto(Trilha t) {
        this.id = t.getId();
        this.name = t.getName();
        this.description = t.getDescription();
    }
}

