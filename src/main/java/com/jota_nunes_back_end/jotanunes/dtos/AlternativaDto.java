package com.jota_nunes_back_end.jotanunes.dtos;

import com.jota_nunes_back_end.jotanunes.models.Alternativas;
import lombok.Data;

@Data
public class AlternativaDto {
    private int id;
    private String texto;
    private Boolean correta;

    public AlternativaDto(Alternativas alternativas) {
        this.id = alternativas.getId();
        this.texto = alternativas.getTexto();
        this.correta = alternativas.getCorreta();
    }
}
