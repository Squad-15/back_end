package com.jota_nunes_back_end.jotanunes.repositories;

import com.jota_nunes_back_end.jotanunes.models.TrilhaModulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ModuloTrilhaRepository extends JpaRepository<TrilhaModulo, Long> {
    List<TrilhaModulo> findByTrilhaIdOrderByOrdemAsc(Long moduloId);

    Optional<TrilhaModulo> findByTrilhaIdAndModuloId(Long id, Long moduloId);

    @Query("SELECT MAX(tm.ordem) FROM TrilhaModulo tm WHERE tm.trilha.id = :trilhaId")
    Integer findMaxOrdemByTrilhaId(@Param("trilhaId") Long trilhaId);
}
