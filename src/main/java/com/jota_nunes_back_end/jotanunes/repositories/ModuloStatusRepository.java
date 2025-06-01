package com.jota_nunes_back_end.jotanunes.repositories;

import com.jota_nunes_back_end.jotanunes.models.StatusModuloUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ModuloStatusRepository extends JpaRepository<StatusModuloUsuario, Long> {
    // Busca todos os status de módulos de um usuário
    List<StatusModuloUsuario> findByUserAccountId(Long usuarioId);

    // Busca status de um módulo específico para um usuário
    Optional<StatusModuloUsuario> findByModuloIdAndUserAccountId(Long moduloId, Long usuarioId);
}
