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
@Table(name = "trilha")
public class Trilha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;


    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "trilha", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CategoriaTrilha> categorias;

    @OneToMany(mappedBy = "trilha", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TrilhaModulo> modulos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<CategoriaTrilha> getCategorias() {
        return categorias;
    }

    public void setCategorias(Set<CategoriaTrilha> categorias) {
        this.categorias = categorias;
    }

    public Set<TrilhaModulo> getModulos() {
        return modulos;
    }

    public void setModulos(Set<TrilhaModulo> modulos) {
        this.modulos = modulos;
    }
}
