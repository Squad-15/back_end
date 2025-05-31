package com.jota_nunes_back_end.jotanunes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

// @Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "questao")
public class Questao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_questao")
    private Integer id;

    @Column(name = "qtd_pontos")
    private Integer qtdPontos;

    @Column(name = "enunciado")
    private String enunciado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQtdPontos() {
        return qtdPontos;
    }

    public void setQtdPontos(Integer qtdPontos) {
        this.qtdPontos = qtdPontos;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_questionario")
    private Questionario questionario;

    @OneToMany(mappedBy = "questao", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Alternativas> alternativas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_alternativa")
    private Alternativas alternativaCorreta;

    @OneToMany(mappedBy = "questao", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioResposta> respostas;
}
