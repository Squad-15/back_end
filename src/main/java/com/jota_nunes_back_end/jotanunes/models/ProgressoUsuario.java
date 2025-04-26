package com.jota_nunes_back_end.jotanunes.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "progresso_usuarios")
public class ProgressoUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_progresso")
    private Integer id;

    @Column(name = "status", length = 50)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private UserAccount usuario;

    @Column(name = "pontuacao_geral")
    private Integer pontuacaoGeral;

    @Column(name = "tempo_online")
    private Duration tempoOnline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_trilha")
    private Trilha trilha;
}
