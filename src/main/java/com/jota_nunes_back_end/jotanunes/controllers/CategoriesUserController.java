package com.jota_nunes_back_end.jotanunes.controllers;

import com.jota_nunes_back_end.jotanunes.models.Categorias;
import com.jota_nunes_back_end.jotanunes.models.UserAccount;
import com.jota_nunes_back_end.jotanunes.repositories.CategoryRepository;
import com.jota_nunes_back_end.jotanunes.repositories.UserAccountRepository;
import com.jota_nunes_back_end.jotanunes.services.CategoryUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class CategoriesUserController {

    private final UserAccountRepository userAccountRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryUserService categoryUserService;

    public CategoriesUserController(UserAccountRepository userAccountRepository,
                                    CategoryRepository categoryRepository, CategoryUserService categoryUserService) {
        this.userAccountRepository = userAccountRepository;
        this.categoryRepository = categoryRepository;
        this.categoryUserService = categoryUserService;
    }

    @PostMapping("/{userId}/categoria/{categoriaId}")
    public ResponseEntity<String> associarCategoriaAoUsuario(@PathVariable Long userId,
                                                             @PathVariable Integer categoriaId) {
        Optional<UserAccount> userOpt = userAccountRepository.findById(userId);
        Optional<Categorias> categoriaOpt = categoryRepository.findById(Long.valueOf(categoriaId));

        if (userOpt.isEmpty() || categoriaOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        UserAccount user = userOpt.get();
        user.setCategoria(categoriaOpt.get());
        userAccountRepository.save(user);

        return ResponseEntity.ok("Categoria associada ao usuário com sucesso.");
    }

    @GetMapping("/{userId}/categoria")
    public ResponseEntity<Object> buscarCategoriaDoUsuario(@PathVariable Long userId) {
        Optional<UserAccount> userOpt = userAccountRepository.findById(userId);

        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Categorias categoria = userOpt.get().getCategoria();

        if (categoria == null) {
            return ResponseEntity.ok().body(Map.of());
        }

        return ResponseEntity.ok(categoria);
    }

    @DeleteMapping("/{userId}/categoria")
    public ResponseEntity<String> removerCategoria(@PathVariable Long userId) {
        boolean removido = categoryUserService.removerCategoriaDoUsuario(userId);

        if (removido) {
            return ResponseEntity.ok("Associação removida com sucesso.");
        } else {
            return ResponseEntity.status(404).body("Usuário não encontrado.");
        }
    }

}
