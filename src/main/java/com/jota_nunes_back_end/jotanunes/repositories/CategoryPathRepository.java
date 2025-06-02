package com.jota_nunes_back_end.jotanunes.repositories;

import com.jota_nunes_back_end.jotanunes.models.CategoriaTrilha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryPathRepository extends JpaRepository<CategoriaTrilha, Long> {
    List<CategoriaTrilha> findByTrilha_Id(Long categoryId);
    List<CategoriaTrilha> findByCategoria_Id(Long categoriaId);
}
