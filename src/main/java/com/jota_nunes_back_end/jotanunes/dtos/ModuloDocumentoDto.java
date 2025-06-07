package com.jota_nunes_back_end.jotanunes.dtos;

import com.jota_nunes_back_end.jotanunes.models.ModuloDocumento;
import lombok.Data;

@Data
public class ModuloDocumentoDto {
    private int idModuloDocumento;
    private int idModulo;
    private String title;
    private String description;
    private String urlPicture;
    private String urlVideo;
    private Integer order;
    private Integer amountPoints;
    private String type;
    private String nameModulo;

    public ModuloDocumentoDto(ModuloDocumento moduloDocumento) {
        this.idModuloDocumento = moduloDocumento.getId();
        this.title = moduloDocumento.getDocumento().getTitulo();
        this.description = moduloDocumento.getDocumento().getDescription();
        this.urlPicture = moduloDocumento.getDocumento().getUrlPhoto();
        this.urlVideo = moduloDocumento.getDocumento().getUrlVideo();
        this.order = moduloDocumento.getDocumento().getOrdem();
        this.type = moduloDocumento.getDocumento().getTipo();
        this.nameModulo = moduloDocumento.getModulo().getName();
        this.amountPoints = moduloDocumento.getDocumento().getQtdPontos();
        this.idModulo = moduloDocumento.getModulo().getId();
    }
}
