package com.jota_nunes_back_end.jotanunes.services;

import com.jota_nunes_back_end.jotanunes.models.Categorias;
import com.jota_nunes_back_end.jotanunes.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    public List<Categorias> listCategories() {
        return categoryRepository.findAll();
    }

    public Categorias createCategory(Categorias categorias) {
        return categoryRepository.save(categorias);
    }

    public Categorias updateCategory(Long id, Categorias newCategories) {
        Categorias categoria = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        categoria.setName(newCategories.getName());
        return categoryRepository.save(categoria);
    }

    public void deleteCategories(Long id) {
        if(!categoryRepository.existsById(id)) {
            throw new RuntimeException("Categoria não encontrada");
        }

        categoryRepository.deleteById(id);
    }
}
