package com.jota_nunes_back_end.jotanunes.repositories;

import com.jota_nunes_back_end.jotanunes.models.CategoriaUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryUserRepository extends JpaRepository<CategoriaUsuario, Long> {
    List<CategoriaUsuario> findByUserAccountId(Long userId);
}
