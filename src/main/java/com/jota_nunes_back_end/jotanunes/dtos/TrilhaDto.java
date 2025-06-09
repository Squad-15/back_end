package com.jota_nunes_back_end.jotanunes.dtos;

import com.jota_nunes_back_end.jotanunes.models.Trilha;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class TrilhaDto {

    private Integer id;

    private String name;

    private String description;

    private String category;


    public TrilhaDto(Trilha t) {
        this.id = t.getId();
        this.name = t.getName();
        this.description = t.getDescription();
        this.category = t.getCategorias()
                .stream()
                .map(c -> c.getCategoria().getName())
                .collect(Collectors.joining(", "));
    }
}
