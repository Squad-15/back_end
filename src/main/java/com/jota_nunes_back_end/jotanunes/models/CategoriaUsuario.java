package com.jota_nunes_back_end.jotanunes.models;

import jakarta.persistence.*;

@Entity
@Table(name = "categoria_usuario")
public class CategoriaUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer id_categoria;

    @Column(name = "id_usuario")
    private Integer id_usuario;

    @ManyToOne
    @JoinColumn(name = "user_account_id", nullable = false)
    private UserAccount userAccount;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categorias categorias;
}
