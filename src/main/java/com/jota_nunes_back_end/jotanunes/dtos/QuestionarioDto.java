package com.jota_nunes_back_end.jotanunes.dtos;

import com.jota_nunes_back_end.jotanunes.models.Questionario;
import lombok.Data;

@Data
public class QuestionarioDto {
    private int id;
    private String type;

    public QuestionarioDto(Questionario questionario) {
        this.id = questionario.getId();
        this.type = questionario.getType();
    }
}
