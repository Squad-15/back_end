package com.jota_nunes_back_end.jotanunes.services;

import com.jota_nunes_back_end.jotanunes.dtos.CategoryDto;
import com.jota_nunes_back_end.jotanunes.models.Categorias;
import com.jota_nunes_back_end.jotanunes.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    public List<CategoryDto> listCategories() {return categoryRepository.findAll().stream().map(CategoryDto::new).toList();}

    public Categorias findById(Long id) {
        return (Categorias) categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria nao encontrada"));
    }


    public Categorias createCategory(Categorias categorias) {
        return categoryRepository.save(categorias);
    }

    public Categorias updateCategory(Long id, Categorias categoriasData) {
        Categorias categorias = findById(id);

        categorias.setName(categoriasData.getName());
        return categoryRepository.save(categorias);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
