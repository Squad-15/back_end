package com.jota_nunes_back_end.jotanunes.dtos;

import com.jota_nunes_back_end.jotanunes.models.StatusModuloUsuario;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ModuloStatusUsuarioDto {
    private Long idStatus;
    private Long userId;
    private Integer moduloId;
    private String status;
    private Double percentage;

//    public ModuloStatusUsuarioDto(StatusModuloUsuario statusModuloUsuario) {
//        this.idStatus = Long.valueOf(statusModuloUsuario.getId());
//        this.userId = statusModuloUsuario.getUserAccount().getId();
//        this.moduloId = Math.toIntExact(statusModuloUsuario.getModulo().getId());
//        this.status = statusModuloUsuario.getStatus();
//        this.percentage = statusModuloUsuario.getPercentualConcluido();
//    }
}
