package com.jota_nunes_back_end.jotanunes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Data
@Table(name = "categoria_trilha")
public class CategoriaTrilha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria_trilha")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
//  @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categorias categoria;
//    private CategoriaUsuario categoriaUsuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_trilha", nullable = false)
    private Trilha trilha;
}
