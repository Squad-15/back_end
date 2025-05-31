package com.jota_nunes_back_end.jotanunes.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
// @Data
@Table(name = "categoria_usuario")
public class CategoriaUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer id_categoria;

    public Integer getIdCategoria() {
        return id_categoria;
    }

    public void setIdCategoria(Integer id_categoria) {
        this.id_categoria = id_categoria;
    }

    @ManyToOne
    @JoinColumn(name = "user_account_id", nullable = false)
    private UserAccount userAccount;

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categorias categorias;

    public Categorias getCategorias() {
        return categorias;
    }

    public void setCategorias(Categorias categorias) {
        this.categorias = categorias;
    }
}
