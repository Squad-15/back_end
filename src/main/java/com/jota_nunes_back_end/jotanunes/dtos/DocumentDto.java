package com.jota_nunes_back_end.jotanunes.dtos;

import com.jota_nunes_back_end.jotanunes.models.Documento;
import lombok.Data;

@Data
public class DocumentDto {
    private int id;
    private String titulo;
    private String description;
    private String url_video;
    private String url_photo;
    private Integer ordem;
    private String tipo;

    public DocumentDto(Documento documento) {
        this.id = documento.getId();
        this.titulo = documento.getTitulo();
        this.description = documento.getDescription();
        this.url_video = documento.getUrl_video();
        this.url_photo = documento.getUrl_photo();
        this.ordem = documento.getOrdem();
        this.tipo = documento.getTipo();
    }
}
