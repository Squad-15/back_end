package com.jota_nunes_back_end.jotanunes.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentoDto {
    private Integer id;
    private String titulo;
    private String url;
    private Integer ordem;
    private String tipo;
    private Integer qtdPontos;
}
