package com.jota_nunes_back_end.jotanunes.models;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "alternativas")
public class Alternativas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alternativa")
    private Integer id;

    @Column(name = "texto")
    private String texto;

    @Column(name = "correta")
    private Boolean correta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_questao")
    private Questao questao;
}
