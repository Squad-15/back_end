package com.jota_nunes_back_end.jotanunes.models;

import jakarta.persistence.*;

@Entity
@Table(name = "categoria_usuario")
public class CategoriaUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria_usuario")
    private Integer id;

    @Column(name = "nome", nullable = false, length = 255)
    private String nome;
}
