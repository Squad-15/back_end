package com.jota_nunes_back_end.jotanunes.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "usuario_resposta")
public class UsuarioResposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resposta_usuario")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_alternativa")
    private Alternativas alternativas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private UserAccount userAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_questionario")
    private Questionario questionario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_questao")
    private Questao questao;

    @Column(name = "pontuacao_recebida")
    private Integer pontuacaoRecebida;

    @Column(name = "data_resposta")
    private LocalDateTime dataResposta;
}
