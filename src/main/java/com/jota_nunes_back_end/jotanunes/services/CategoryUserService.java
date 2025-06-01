package com.jota_nunes_back_end.jotanunes.services;

import com.jota_nunes_back_end.jotanunes.models.CategoriaUsuario;
import com.jota_nunes_back_end.jotanunes.repositories.CategoryUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryUserService {
    @Autowired
    private  CategoryUserRepository categoryUserRepository;

    public CategoryUserService(CategoryUserRepository categoryUserRepository) {
        this.categoryUserRepository = categoryUserRepository;
    }

    @Transactional
    public CategoriaUsuario associateUserToCategory(CategoriaUsuario categoriaUsuario) {
        return categoryUserRepository.save(categoriaUsuario);
    }

    @Transactional
    public void deleteAssociation(Long userAccountCategoryId) {
        categoryUserRepository.deleteById(userAccountCategoryId);
    }

    @Transactional
    public List<CategoriaUsuario> listPerUser(Long userId) {
        return categoryUserRepository.findByUserAccount_Id(userId);
    }
}