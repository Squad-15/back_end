package com.jota_nunes_back_end.jotanunes.models;

import com.jota_nunes_back_end.jotanunes.enums.StatusProgresso;
import jakarta.persistence.*;

import java.time.LocalDate;
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
@Table(name = "status_modulos_usuarios")
public class StatusModuloUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_status")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private UserAccount userAccount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusProgresso status;

    @Column(name = "percentual_concluido")
    private Double percentualConcluido;

    @Column(name = "data_conclusao")
    private LocalDate dataConclusao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_modulo")
    private Modulo modulo;

    public void setDataConclusao(LocalDateTime now) {
        this.dataConclusao = now.toLocalDate();
    }

}
