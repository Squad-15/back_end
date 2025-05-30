package com.jota_nunes_back_end.jotanunes.controllers;

import com.jota_nunes_back_end.jotanunes.models.CategoriaUsuario;
import com.jota_nunes_back_end.jotanunes.models.Categorias;
import com.jota_nunes_back_end.jotanunes.models.UserAccount;
import com.jota_nunes_back_end.jotanunes.repositories.CategoryRepository;
import com.jota_nunes_back_end.jotanunes.repositories.CategoryUserRepository;
import com.jota_nunes_back_end.jotanunes.repositories.UserAccountRepository;
import com.jota_nunes_back_end.jotanunes.services.CategoryService;
import com.jota_nunes_back_end.jotanunes.services.CategoryUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user/{userId}/categories")
public class CategoriesUserController {
    private final UserAccountRepository userAccountRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryUserRepository categoryUserRepository;
    private  CategoryService categoryService;

//    public CategoriesUserController(CategoryService categoryService,
//                                    UserAccountRepository userAccountRepository,
//                                    CategoryRepository categoryRepository,
//                                    CategoryUserRepository categoryUserRepository) {
//        this.categoryService = categoryService;
//        this.userAccountRepository = userAccountRepository;
//        this.categoryRepository = categoryRepository;
//        this.categoryUserRepository = categoryUserRepository;
//    }

    private final CategoryUserService categoryUserService;

    public CategoriesUserController(CategoryService categoryService,
                                    UserAccountRepository userAccountRepository,
                                    CategoryRepository categoryRepository,
                                    CategoryUserRepository categoryUserRepository,
                                    CategoryUserService categoryUserService) {
        this.categoryService = categoryService;
        this.userAccountRepository = userAccountRepository;
        this.categoryRepository = categoryRepository;
        this.categoryUserRepository = categoryUserRepository;
        this.categoryUserService = categoryUserService;
    }



    @GetMapping
    public Optional<CategoriaUsuario> getCategories(@PathVariable int userId){
        return categoryUserService.listPerUser(Long.valueOf(userId));
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

        CategoriaUsuario categoriaUsuario = new CategoriaUsuario();
        categoriaUsuario.setUserAccount(user);
        categoriaUsuario.setCategorias(categoria);

        categoryUserService.associateUserToCategory(categoriaUsuario);

        return ResponseEntity.ok("Categoria associada");

    }

}
