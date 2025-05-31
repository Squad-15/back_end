package com.jota_nunes_back_end.jotanunes.dtos;

import com.jota_nunes_back_end.jotanunes.models.Questao;

public class QuestaoDto {
    private int id;
    private Integer qtdPontos;
    private String enunciado;

    public QuestaoDto(Questao questao) {
        this.id = questao.getId();
        this.qtdPontos = questao.getQtdPontos();
        this.enunciado = questao.getEnunciado();
    }
}
