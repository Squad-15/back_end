package com.jota_nunes_back_end.jotanunes.dtos;

import com.jota_nunes_back_end.jotanunes.models.Trilha;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TrilhaDto {

    private Integer id;

    @NotBlank(message = "O nome é obrigatório.")
    private String name;

    @NotBlank(message = "A descrição é obrigatória.")
    private String description;

    public TrilhaDto(Trilha t) {
        this.id = t.getId();
        this.name = t.getName();
        this.description = t.getDescription();
    }
}
