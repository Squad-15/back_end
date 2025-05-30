package com.jota_nunes_back_end.jotanunes.controllers;

import com.jota_nunes_back_end.jotanunes.dtos.CategoriaTrilhaDto;
import com.jota_nunes_back_end.jotanunes.models.Categorias;
import com.jota_nunes_back_end.jotanunes.models.Trilha;
import com.jota_nunes_back_end.jotanunes.models.CategoriaTrilha;
import com.jota_nunes_back_end.jotanunes.repositories.CategoryPathRepository;
import com.jota_nunes_back_end.jotanunes.repositories.CategoryRepository;
import com.jota_nunes_back_end.jotanunes.repositories.TrilhaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trilhas/{trilhaId}/categories")
public class CategoryPathController {

    private final CategoryRepository categoryRepository;
    private final TrilhaRepository trilhaRepository;
    private final CategoryPathRepository categoryPathRepository;

    public CategoryPathController(CategoryRepository categoryRepository,
                                  TrilhaRepository trilhaRepository,
                                  CategoryPathRepository categoryPathRepository) {
        this.categoryRepository = categoryRepository;
        this.trilhaRepository = trilhaRepository;
        this.categoryPathRepository = categoryPathRepository;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaTrilhaDto>> getCategoriesByTrilha(@PathVariable Long trilhaId) {
        List<CategoriaTrilha> categoriasTrilha = categoryPathRepository.findByTrilha_Id(trilhaId);
        List<CategoriaTrilhaDto> dtos = categoriasTrilha.stream()
                .map(CategoriaTrilhaDto::new)
                .toList();
        return ResponseEntity.ok(dtos);
    }


    @PostMapping("/{categoryId}")
    public ResponseEntity<String> associateCategoryToTrilha(@PathVariable Long trilhaId, @PathVariable Long categoryId) {
        Optional<Trilha> trilhaOpt = trilhaRepository.findById(trilhaId);
        Optional<Categorias> categoriaOpt = categoryRepository.findById(categoryId);

        if (trilhaOpt.isEmpty() || categoriaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        CategoriaTrilha categoriaTrilha = new CategoriaTrilha();
        categoriaTrilha.setTrilha(trilhaOpt.get());
        categoriaTrilha.setCategoria(categoriaOpt.get());

        categoryPathRepository.save(categoriaTrilha);

        return ResponseEntity.ok("Categoria associada à trilha com sucesso.");
    }

//    @DeleteMapping("/{categoryId}")
//    public ResponseEntity<String> deleteCategory(@PathVariable Long trilhaId, @PathVariable Long categoryId) {
//        CategoriaTrilha categoriaTrilha = new CategoriaTrilha();
//        categoriaTrilha.setTrilha(trilhaId);
//        categoriaTrilha.setCategoria(categoryId);
//
//        if(!categoryPathRepository.existsById(categoryId)) {
//            return ResponseEntity.notFound().build();
//        }
//
//        categoryPathRepository.delete(categoriaTrilha);
//        return ResponseEntity.ok("Categoria apagada com sucesso.");
//    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long trilhaId, @PathVariable Long categoryId) {
        Optional<Trilha> trilhaOpt = trilhaRepository.findById(trilhaId);
        Optional<Categorias> categoriaOpt = categoryRepository.findById(categoryId);

        if (trilhaOpt.isEmpty() || categoriaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        CategoriaTrilha categoriaTrilha = new CategoriaTrilha();
        categoriaTrilha.setTrilha(trilhaOpt.get());
        categoriaTrilha.setCategoria(categoriaOpt.get());

        categoryPathRepository.delete(categoriaTrilha);
        return ResponseEntity.ok("Categoria apagada com sucesso.");
    }

}
