package com.jota_nunes_back_end.jotanunes.repositories;

import com.jota_nunes_back_end.jotanunes.models.ModuloDocumento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ModuloDocumentoRepository extends JpaRepository<ModuloDocumento, Long> {
    List<ModuloDocumento> findByModulo_Id(Long moduloId);
    Optional<ModuloDocumento> findByDocumento_IdAndModulo_Id(Long id, Long moduloId);
}
