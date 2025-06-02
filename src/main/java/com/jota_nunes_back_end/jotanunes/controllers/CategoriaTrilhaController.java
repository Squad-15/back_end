package com.jota_nunes_back_end.jotanunes.controllers;

import com.jota_nunes_back_end.jotanunes.dtos.CategoriaTrilhaDto;
import com.jota_nunes_back_end.jotanunes.models.CategoriaTrilha;
import com.jota_nunes_back_end.jotanunes.repositories.CategoryPathRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaTrilhaController {

    private final CategoryPathRepository categoryPathRepository;

    public CategoriaTrilhaController(CategoryPathRepository categoryPathRepository) {
        this.categoryPathRepository = categoryPathRepository;
    }

    @GetMapping("/{categoriaId}/trilhas")
    public ResponseEntity<List<CategoriaTrilhaDto>> getTrilhasByCategoria(@PathVariable Long categoriaId) {
        List<CategoriaTrilha> trilhasCategoria = categoryPathRepository.findByCategoria_Id(categoriaId);
        List<CategoriaTrilhaDto> dtos = trilhasCategoria.stream()
                .map(CategoriaTrilhaDto::new)
                .toList();
        return ResponseEntity.ok(dtos);
    }
}
