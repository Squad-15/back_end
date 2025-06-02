package com.jota_nunes_back_end.jotanunes.repositories;

import com.jota_nunes_back_end.jotanunes.models.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryUserRepository extends JpaRepository<Categorias, Integer> {}

