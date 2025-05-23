package com.jota_nunes_back_end.jotanunes.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "categorias")
public class Categorias {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer id_categoria;

    @Column(name = "nome", nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "categorias")
    private List<CategoriaUsuario> categoriaUsuarios;

}
