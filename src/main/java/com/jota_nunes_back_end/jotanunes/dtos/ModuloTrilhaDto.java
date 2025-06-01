package com.jota_nunes_back_end.jotanunes.dtos;

import com.jota_nunes_back_end.jotanunes.models.TrilhaModulo;
import lombok.Data;

@Data
public class ModuloTrilhaDto {
    private int id;
    private Long idTrilha;
    private Long idModulo;
    private String nomeModulo;
    private Integer order;
    private String descricaoModulo;
    private String nameTrilha;
    private String descricaoTrilha;

    public ModuloTrilhaDto(TrilhaModulo trilhaModulo) {
        this.id = trilhaModulo.getId();
        this.idTrilha = Long.valueOf(trilhaModulo.getTrilha().getId());
        this.idModulo = Long.valueOf(trilhaModulo.getModulo().getId());
        this.nomeModulo = trilhaModulo.getModulo().getName();
        this.descricaoModulo = trilhaModulo.getModulo().getDescription();
        this.nameTrilha = trilhaModulo.getTrilha().getName();
        this.descricaoTrilha = trilhaModulo.getTrilha().getDescription();
        this.order = trilhaModulo.getOrdem();
    }
}
