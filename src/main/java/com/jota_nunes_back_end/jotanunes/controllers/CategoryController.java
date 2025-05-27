package com.jota_nunes_back_end.jotanunes.controllers;


import com.jota_nunes_back_end.jotanunes.dtos.CategoryDto;
import com.jota_nunes_back_end.jotanunes.models.Categorias;
import com.jota_nunes_back_end.jotanunes.repositories.CategoryRepository;
import com.jota_nunes_back_end.jotanunes.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<CategoryDto> Show() {
        return categoryRepository.findAll().stream()
                .map(CategoryDto::new)
                .toList();
    }

    @GetMapping("/{id}")
    public Categorias findByIdDto(@PathVariable int id) {
        Categorias categorias = categoryService.findById((long) id);
        return categorias;
    }

    @PostMapping()
    public ResponseEntity<Categorias> createCategory(@RequestBody @Valid Categorias categorias) {
        Categorias newCategorias = categoryService.createCategory(categorias);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategorias);

    }

    @PutMapping("/{id}")
    public Categorias updateCategory(@PathVariable long id, @RequestBody @Valid Categorias categorias) {return  categoryService.updateCategory(id, categorias);}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

}
