package com.jota_nunes_back_end.jotanunes.controllers;

import com.jota_nunes_back_end.jotanunes.dtos.UserCategoryDto;
import com.jota_nunes_back_end.jotanunes.models.CategoriaUsuario;
import com.jota_nunes_back_end.jotanunes.models.Categorias;
import com.jota_nunes_back_end.jotanunes.models.UserAccount;
import com.jota_nunes_back_end.jotanunes.repositories.CategoryRepository;
import com.jota_nunes_back_end.jotanunes.repositories.CategoryUserRepository;
import com.jota_nunes_back_end.jotanunes.repositories.UserAccountRepository;
import com.jota_nunes_back_end.jotanunes.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import java.util.List;

@RestController
@RequestMapping("/user/{userId}/categories")
public class CategoriesUserController {
    private final UserAccountRepository userAccountRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryUserRepository categoryUserRepository;
    private  CategoryService categoryService;

    public CategoriesUserController(UserAccountRepository userAccountRepository,
                                    CategoryRepository categoryRepository,
                                    CategoryUserRepository categoryUserRepository) {
        this.userAccountRepository = userAccountRepository;
        this.categoryRepository = categoryRepository;
        this.categoryUserRepository = categoryUserRepository;
    }

    @GetMapping
    public ResponseEntity<List<UserCategoryDto>> getCategoriasDoUsuario(@PathVariable Long userId) {
        List<CategoriaUsuario> categorias = categoryUserRepository.findByUserAccount_Id(userId);
        List<UserCategoryDto> dtos = categorias.stream()
                .map(UserCategoryDto::new)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/{categoryId}")
    public ResponseEntity<String> associarCategoriaAoUsuario(@PathVariable Long userId, @PathVariable Long categoryId) {
        Optional<UserAccount> userOpt = userAccountRepository.findById(userId);
        Optional<Categorias> categoriaOpt = categoryRepository.findById(categoryId);

        if (userOpt.isEmpty() || categoriaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        CategoriaUsuario categoriaUsuario = new CategoriaUsuario();
        categoriaUsuario.setUserAccount(userOpt.get());
        categoriaUsuario.setCategorias(categoriaOpt.get());
        categoryUserRepository.save(categoriaUsuario);

        return ResponseEntity.ok("Categoria associada ao usuário com sucesso.");
    }


    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> removeUserFromCategory(@PathVariable Long userId, @PathVariable Long categoryId) {
        Optional<CategoriaUsuario> categoriaUsuario = categoryUserRepository.findByUserAccount_IdAndCategorias_Id(userId, categoryId);

        if (categoriaUsuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        categoryUserRepository.delete(categoriaUsuario.get());
        return ResponseEntity.ok("Categoria removida com sucesso!");
    }
}
