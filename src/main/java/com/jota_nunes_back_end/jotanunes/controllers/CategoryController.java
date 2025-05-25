package com.jota_nunes_back_end.jotanunes.controllers;


import com.jota_nunes_back_end.jotanunes.models.Categorias;
import com.jota_nunes_back_end.jotanunes.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Categorias>> Show() {
        return ResponseEntity.ok(categoryService.listCategories());
    }

    @PostMapping()
    public ResponseEntity<Categorias> create(@RequestBody Categorias categorias) {
        return ResponseEntity.ok(categoryService.createCategory(categorias));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categorias> edit(@PathVariable Integer id, @RequestBody Categorias categorias) {
        return ResponseEntity.ok(categoryService.updateCategory(Long.valueOf(id), categorias));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.deleteCategories(id);
        return ResponseEntity.noContent().build();
    }
}
