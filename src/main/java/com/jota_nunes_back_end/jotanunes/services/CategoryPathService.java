package com.jota_nunes_back_end.jotanunes.services;

import com.jota_nunes_back_end.jotanunes.models.CategoriaTrilha;
import com.jota_nunes_back_end.jotanunes.models.CategoriaUsuario;
import com.jota_nunes_back_end.jotanunes.repositories.CategoryPathRepository;
import com.jota_nunes_back_end.jotanunes.repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryPathService {
    @Autowired
    private CategoryPathRepository categoryPathRepository;

    public CategoryPathService(CategoryPathRepository categoryPathRepository) {
        this.categoryPathRepository = categoryPathRepository;
    }

    @Transactional
    public CategoriaTrilha associateCategoryToPath(CategoriaTrilha categoriaTrilha) {
        return categoryPathRepository.save(categoriaTrilha);
    }

    @Transactional
    public void deleteAssociation(Long categoriaTrilhaId) {
        categoryPathRepository.deleteById(categoriaTrilhaId);
    }

    @Transactional
    public List<CategoriaTrilha> showPerCategory(Long categoryId) {
        return categoryPathRepository.findByTrilha_Id(categoryId);
    }
}
