//package com.jota_nunes_back_end.jotanunes.repositories;
//
//import com.jota_nunes_back_end.jotanunes.models.CategoriaUsuario;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface CategoryUserRepository extends JpaRepository<CategoriaUsuario, Long> {
//    List<CategoriaUsuario> findByUserAccount_Id(Long userId);
//    Optional<CategoriaUsuario> findByUserAccount_IdAndCategorias_Id(Long userId, Long categoriaUsuarioId);;
//}

package com.jota_nunes_back_end.jotanunes.repositories;

import com.jota_nunes_back_end.jotanunes.models.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryUserRepository extends JpaRepository<Categorias, Integer> {}

