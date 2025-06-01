package com.jota_nunes_back_end.jotanunes.dtos;

import com.jota_nunes_back_end.jotanunes.models.TrilhaQuestionario;
import lombok.Data;

@Data
public class TrilhaQuestionarioDto {
    private int idTrilhaQuestionario;
    private int idTrilha;
    private int idQuestionario;
    private String nameTrilha;
    private String typeQuestionario;

    public TrilhaQuestionarioDto(TrilhaQuestionario trilhaQuestionario) {
        this.idTrilhaQuestionario = trilhaQuestionario.getId();
        this.nameTrilha = trilhaQuestionario.getTrilha().getName();
        this.typeQuestionario = trilhaQuestionario.getQuestionario().getType();
        this.idTrilha = trilhaQuestionario.getTrilha().getId();
        this.idQuestionario = trilhaQuestionario.getQuestionario().getId();
    }
}
