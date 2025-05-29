package com.jota_nunes_back_end.jotanunes.controllers;

import com.jota_nunes_back_end.jotanunes.models.CategoriaUsuario;
import com.jota_nunes_back_end.jotanunes.models.Categorias;
import com.jota_nunes_back_end.jotanunes.models.UserAccount;
import com.jota_nunes_back_end.jotanunes.repositories.CategoryRepository;
import com.jota_nunes_back_end.jotanunes.repositories.UserAccountRepository;
import com.jota_nunes_back_end.jotanunes.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user/{userId}/categories")
public class CategoriesUserController {
    private final UserAccountRepository userAccountRepository;
    private final CategoryRepository categoryRepository;
    private  CategoryService categoryService;

    public CategoriesUserController(CategoryService categoryService, UserAccountRepository userAccountRepository, CategoryRepository categoryRepository) {
        this.categoryService = categoryService;
        this.userAccountRepository = userAccountRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public Optional<Categorias> getCategories(@PathVariable int userId){
        return categoryService.listPerUser(Long.valueOf(userId));
    }

    @PostMapping("/{categoryId}")
    public ResponseEntity<?> associateUserToCategory(@PathVariable Long userId, @PathVariable Long categoryId) {
        Optional<UserAccount> userOpt = userAccountRepository.findById(userId);
        Optional<Categorias> categoryOpt = categoryRepository.findById(categoryId);

        if (userOpt.isEmpty() || categoryOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        UserAccount user = userOpt.get();
        Categorias categoria = categoryOpt.get();

        categoria.setUser(user); // Associação feita aqui
        categoryRepository.save(categoria); // Agora sim salva com o relacionamento

        return ResponseEntity.ok("Categoria associada ao usuário com sucesso!");
    }

}
